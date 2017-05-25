/**
 * 
 */
package com.future.tech.captain.mq.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;

import com.future.tech.captain.mq.MessageSender;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Title: MessageSenderRocketMQImpl.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company:  FutureTech<br>
 * 
 * @author weilai
 * May 23, 2017
 */

@Slf4j
public class MessageSenderRocketMQImpl implements MessageSender{
	
	@Autowired
	private DefaultMQProducer producer;
	
	@Setter
	private String topic;
	
	@Setter
	private String tags;
	
	@Autowired
	private MessageCallback messageCallback;

	/* (non-Javadoc)
	 * @see com.future.tech.captain.mq.MessageSender#send(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean send(String correlationId, Object message) {
		Message rocketMsg = new Message(topic, tags, correlationId, (byte[])message);
		try {
			producer.send(rocketMsg, messageCallback);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.future.tech.captain.mq.MessageSender#isSynConfirm()
	 */
	@Override
	public boolean isSynConfirm() {
		return false;
	}

}
