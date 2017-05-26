/**
 * 
 */
package com.future.tech.captain.mq.activemq;

import org.springframework.jms.core.JmsTemplate;

import com.future.tech.captain.mq.MessageSender;

/**
 * 
 * Title: MessageSenderActiveMQImpl.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company:  FutureTech<br>
 * 
 * @author weilai
 * May 26, 2017
 */

public class MessageSenderActiveMQImpl implements MessageSender{

	private JmsTemplate jmsTemplate;
	/* (non-Javadoc)
	 * @see com.future.tech.captain.mq.MessageSender#send(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean send(String correlationId, Object message) {
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.future.tech.captain.mq.MessageSender#isSynConfirm()
	 */
	@Override
	public boolean isSynConfirm() {
		// TODO Auto-generated method stub
		return false;
	}

}
