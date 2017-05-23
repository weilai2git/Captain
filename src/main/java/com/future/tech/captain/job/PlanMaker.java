/**
 * 
 */
package com.future.tech.captain.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.future.tech.captain.api.CorrelationData;
import com.future.tech.captain.api.MessageConfirmChecker;
import com.future.tech.captain.config.CaptainConfig;
import com.future.tech.captain.domain.MessageWrapper;
import com.future.tech.captain.domain.MessageWrapperIdentity;
import com.future.tech.captain.repository.MessageRepository;

/**
 * 
 * Title: PlanMaker.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company: FutureTech<br>
 * 
 * @author weilai May 22, 2017
 */

@Component("defaultPlanMaker")
public class PlanMaker {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private CaptainConfig config;

	public void makeAndDoPlan() {
		List<MessageWrapper> messageList = messageRepository.findByAppName(config.getAppName(), config.getPlanLimit());
		if (!CollectionUtils.isEmpty(messageList)) {
			for (MessageWrapper messageWrapper : messageList) {
				MessageConfirmChecker messageConfirmChecker = config
						.findMessageConfirmChecker(messageWrapper.getMessageConfirmCheckerName());
				CorrelationData correlationData = new CorrelationData();
				MessageWrapperIdentity messageWrapperIdentity = new MessageWrapperIdentity(messageWrapper.getId());
				correlationData.setId(messageWrapperIdentity.getOriginId());
				correlationData.setMessageConfirmCheckerName(messageWrapper.getMessageConfirmCheckerName());
				correlationData.setMessageSenderName(messageWrapper.getMessageSenderName());
				if (messageConfirmChecker.isMessageNeed2Send(correlationData)) {
					config.findMessageSender(messageWrapper.getMessageSenderName()).send(messageWrapper.getId(),
							messageWrapper.getMessage());
				}
				messageRepository.remove(messageWrapper.getIdentity());
			}
		}
	}
}
