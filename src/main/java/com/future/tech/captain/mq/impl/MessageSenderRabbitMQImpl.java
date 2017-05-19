/**
 * 
 */
package com.future.tech.captain.mq.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.future.tech.captain.mq.MessageSender;

/**
 * 
 * Title: MessageSenderRabbitMQImpl.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company:  FutureTech<br>
 * 
 * @author weilai
 * May 19, 2017
 */
@Component
public class MessageSenderRabbitMQImpl implements MessageSender{
	
	@Autowired
	private AmqpTemplate amqpTemplate;

	/* (non-Javadoc)
	 * @see com.future.tech.captain.mq.MessageSender#send(java.lang.Object)
	 */
	@Override
	public void send(Object message) {
		amqpTemplate.convertAndSend(message);
		System.out.println("Send Msg Succ!");
	}

}
