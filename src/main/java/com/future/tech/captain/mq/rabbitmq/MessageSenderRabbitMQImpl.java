/**
 * 
 */
package com.future.tech.captain.mq.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.future.tech.captain.mq.MessageSender;

/**
 * 
 * Title: MessageSenderRabbitMQImpl.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company: FutureTech<br>
 * 
 * @author weilai May 19, 2017
 */
@Component("captain.defaultRabbitMQSender")
public class MessageSenderRabbitMQImpl implements MessageSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.future.tech.captain.mq.MessageSender#send(java.lang.Object)
	 */
	@Override
	public boolean send(String correlationId, Object message) {
		CorrelationData correlationData = new CorrelationData(correlationId);
		rabbitTemplate.correlationConvertAndSend(message, correlationData);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.future.tech.captain.mq.MessageSender#isAsynConfirm()
	 */
	@Override
	public boolean isSynConfirm() {
		return false;
	}

}
