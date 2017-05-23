/**
 * 
 */
package com.future.tech.captain.mq.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Title: MessageReturnCallback.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company: FutureTech<br>
 * 
 * @author weilai May 22, 2017
 */
@Slf4j
@Component("captain.defaultRabbitMqReturnCallback")
public class MessageReturnCallback implements RabbitTemplate.ReturnCallback {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback#
	 * returnedMessage(org.springframework.amqp.core.Message, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		// now do nothing but add log
		log.error("Can not find queue, replyCode = " + replyCode + ", replyText = " + replyText + ", exchange = "
				+ exchange);
	}

}
