/**
 * 
 */
package com.future.tech.captain.factory;

import org.springframework.stereotype.Component;

import com.future.tech.captain.api.CorrelationData;
import com.future.tech.captain.domain.MessageWrapper;

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

	public MessageWrapper make(CorrelationData correlationData, Object message) {
		MessageWrapper messageWrapper = new MessageWrapper();
		messageWrapper.setId(correlationData.getId());
		messageWrapper.setQName(correlationData.getRefQueueName());
		messageWrapper.setMessage(message);
		return messageWrapper;
	}
}
