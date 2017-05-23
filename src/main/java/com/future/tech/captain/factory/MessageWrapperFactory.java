/**
 * 
 */
package com.future.tech.captain.factory;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.future.tech.captain.api.CorrelationData;
import com.future.tech.captain.domain.MessageWrapper;
import com.future.tech.captain.domain.MessageWrapperIdentity;
import com.future.tech.captain.enums.MessageWrapperStatus;

/**
 * 
 * Title: MessageWrapperFactory.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company: FutureTech<br>
 * 
 * @author weilai May 19, 2017
 */
@Component
public class MessageWrapperFactory {

	public MessageWrapper make(String appName, CorrelationData correlationData, Object message) {
		MessageWrapper messageWrapper = new MessageWrapper();
		messageWrapper.setId(new MessageWrapperIdentity(appName, correlationData.getId()).getId());
		messageWrapper.setAppName(appName);
		messageWrapper.setMessageSenderName(correlationData.getMessageSenderName());
		messageWrapper.setMessageConfirmCheckerName(correlationData.getMessageConfirmCheckerName());
		messageWrapper.setMessage(message);
		messageWrapper.setStatus(MessageWrapperStatus.PREPARED.getCode());
		messageWrapper.setCreatedDate(new Date());
		return messageWrapper;
	}
}
