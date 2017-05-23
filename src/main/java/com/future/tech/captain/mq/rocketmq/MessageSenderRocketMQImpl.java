/**
 * 
 */
package com.future.tech.captain.mq.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;

import com.future.tech.captain.mq.MessageSender;

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

public class MessageSenderRocketMQImpl implements MessageSender{
	
	@Autowired
	private DefaultMQProducer producer;

	/* (non-Javadoc)
	 * @see com.future.tech.captain.mq.MessageSender#send(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean send(String correlationId, Object message) {
		//producer.send(message, new SendCal);
		return false;
	}

	/* (non-Javadoc)
	 * @see com.future.tech.captain.mq.MessageSender#isSynConfirm()
	 */
	@Override
	public boolean isSynConfirm() {
		return false;
	}

}
