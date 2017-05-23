/**
 * 
 */
package com.future.tech.captain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.future.tech.captain.api.CorrelationData;
import com.future.tech.captain.config.CaptainConfig;
import com.future.tech.captain.domain.MessageWrapper;
import com.future.tech.captain.domain.MessageWrapperIdentity;
import com.future.tech.captain.factory.MessageWrapperFactory;
import com.future.tech.captain.mq.MessageSender;
import com.future.tech.captain.repository.MessageRepository;
import com.future.tech.captain.service.ReliableMessageService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Title: ReliableMessageServiceImpl.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company: FutureTech<br>
 * 
 * @author weilai May 19, 2017
 */
@Service
@Slf4j
public class ReliableMessageServiceImpl implements ReliableMessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private MessageWrapperFactory messageWrapperFactory;

	@Autowired
	private CaptainConfig config;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.future.tech.captain.service.ReliableMessageService#prepare(com.future
	 * .tech.captain.api.CorrelationData, java.lang.Object)
	 */
	@Override
	public void prepare(CorrelationData correlationData, Object message) {
		MessageWrapper messageWrapper = messageWrapperFactory.make(config.getAppName(), correlationData, message);
		messageRepository.store(messageWrapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.future.tech.captain.service.ReliableMessageService#confirm(com.future
	 * .tech.captain.api.CorrelationData)
	 */
	@Override
	public void confirm(CorrelationData correlationData) {
		MessageWrapperIdentity messageWrapperIdentity = new MessageWrapperIdentity(config.getAppName(),
				correlationData.getId());
		MessageWrapper messageWrapper = messageRepository.loadMessage(messageWrapperIdentity);
		if ( messageWrapper != null && messageWrapper.isReady2Confirm() ) {
			MessageSender messageSender = config.findMessageSender(messageWrapper.getMessageSenderName());
			if (messageSender == null ) {
				// if can not find message sender, do nothing here
				return;
			}
			if (messageSender.send(messageWrapper.getId(), messageWrapper.getMessage()) && messageSender.isSynConfirm()) {
				messageWrapper.confirm();
				messageRepository.store(messageWrapper);
			}
		} else {
			log.error("Confirm ERROR!, msgWrapper = " + messageWrapper);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.future.tech.captain.service.ReliableMessageService#cancel(com.future.
	 * tech.captain.api.CorrelationData)
	 */
	@Override
	public void cancel(CorrelationData correlationData) {
		MessageWrapperIdentity messageWrapperIdentity = new MessageWrapperIdentity(config.getAppName(),
				correlationData.getId());
		MessageWrapper messageWrapper = messageRepository.loadMessage(messageWrapperIdentity);
		if ( messageWrapper != null  && messageWrapper.isReady2Cancel() ) {
			messageWrapper.cancel();
			messageRepository.store(messageWrapper);
		} else {
			log.error("Cancel ERROR!, msgWrapper = " + messageWrapper);
		}
	}
}
