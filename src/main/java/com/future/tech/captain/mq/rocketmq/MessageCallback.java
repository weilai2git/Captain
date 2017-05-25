/**
 * 
 */
package com.future.tech.captain.mq.rocketmq;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.future.tech.captain.domain.MessageWrapper;
import com.future.tech.captain.domain.MessageWrapperIdentity;
import com.future.tech.captain.repository.MessageRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Title: MessageCallback.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company:  FutureTech<br>
 * 
 * @author weilai
 * May 23, 2017
 */
@Component
@Slf4j
public class MessageCallback implements SendCallback{
	
	@Autowired
	private MessageRepository messageRepository;

	/* (non-Javadoc)
	 * @see org.apache.rocketmq.client.producer.SendCallback#onSuccess(org.apache.rocketmq.client.producer.SendResult)
	 */
	@Override
	public void onSuccess(SendResult sendResult) {
		MessageWrapperIdentity messageWrapperIdentity = new MessageWrapperIdentity(sendResult.getMsgId());
		MessageWrapper messageWrapper = messageRepository.loadMessage(messageWrapperIdentity);
		if ( messageWrapper != null && messageWrapper.confirm() ) {
			messageRepository.store(messageWrapper);
		}
	}

	/* (non-Javadoc)
	 * @see org.apache.rocketmq.client.producer.SendCallback#onException(java.lang.Throwable)
	 */
	@Override
	public void onException(Throwable e) {
		log.error(e.getMessage(), e);
	}

}
