/**
 * 
 */
package com.future.tech.captain.mq.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.future.tech.captain.domain.MessageWrapper;
import com.future.tech.captain.domain.MessageWrapperIdentity;
import com.future.tech.captain.repository.MessageRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Title: MessageConfirmCallback.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company: FutureTech<br>
 * 
 * @author weilai May 22, 2017
 */
@Slf4j
@Component("captain.defaultRabbitMqConfirmCallback")
public class MessageConfirmCallback implements RabbitTemplate.ConfirmCallback {

	@Autowired
	private MessageRepository messageRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback#
	 * confirm(org.springframework.amqp.rabbit.support.CorrelationData, boolean,
	 * java.lang.String)
	 */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack) {
		if (ack) {
			// remove message
			MessageWrapperIdentity messageWrapperIdentity = new MessageWrapperIdentity(correlationData.getId());
			MessageWrapper messageWrapper = messageRepository.loadMessage(messageWrapperIdentity);
			if ( messageWrapper != null && messageWrapper.confirm() ) {
				messageRepository.store(messageWrapper);
			}
		} else {
			// wait for retry
			log.error("Send Message Error, CorrelationData.id = " + correlationData.getId() );
		}

	}

}
