/**
 * 
 */
package com.future.tech.captain.mq.activemq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.SessionCallback;

import com.future.tech.captain.domain.MessageWrapper;
import com.future.tech.captain.domain.MessageWrapperIdentity;
import com.future.tech.captain.mq.MessageSender;
import com.future.tech.captain.repository.MessageRepository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

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

@Slf4j
public class MessageSenderActiveMQImpl implements MessageSender{

	@Setter
	private JmsTemplate jmsTemplate;
	
	@Setter
	private Destination destination;
	
	@Autowired
	private MessageRepository messageRepository;

	/* (non-Javadoc)
	 * @see com.future.tech.captain.mq.MessageSender#send(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean send(final String correlationId, final Object message) {
		jmsTemplate.execute(new SessionCallback<Object>() {
			@Override
			public Object doInJms(Session session) throws JMSException {
				ActiveMQMessageProducer producer =(ActiveMQMessageProducer)session.createProducer(destination);
				Message msg = session.createTextMessage(String.valueOf(message));
				producer.send(msg, new AsyncCallback() {
					@Override
					public void onException(JMSException e) {
						log.error(e.getMessage(), e);
					}
					@Override
					public void onSuccess() {
						MessageWrapperIdentity messageWrapperIdentity = new MessageWrapperIdentity(correlationId);
						MessageWrapper messageWrapper = messageRepository.loadMessage(messageWrapperIdentity);
						if (messageWrapper != null && messageWrapper.confirm()) {
							messageRepository.store(messageWrapper);
						}
					}
				});
				return null;
			}
		});
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
