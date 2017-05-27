/**
 * 
 */
package com.future.tech.captain.mq.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;

import com.future.tech.captain.domain.MessageWrapper;
import com.future.tech.captain.domain.MessageWrapperIdentity;
import com.future.tech.captain.mq.MessageSender;
import com.future.tech.captain.repository.MessageRepository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Title: MessageSenderRocketMQImpl.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company: FutureTech<br>
 * 
 * @author weilai May 23, 2017
 */

@Slf4j
public class MessageSenderRocketMQImpl implements MessageSender {

	@Setter
	private DefaultMQProducer producer;

	@Setter
	private String topic;

	@Setter
	private String tags;

	@Autowired
	private MessageRepository messageRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.future.tech.captain.mq.MessageSender#send(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	public boolean send(final String correlationId, Object message) {
		Message rocketMsg = new Message(topic, tags, correlationId, (byte[]) message);
		try {
			producer.send(rocketMsg, new SendCallback() {
				@Override
				public void onSuccess(SendResult sendResult) {
					MessageWrapperIdentity messageWrapperIdentity = new MessageWrapperIdentity(correlationId);
					MessageWrapper messageWrapper = messageRepository.loadMessage(messageWrapperIdentity);
					if (messageWrapper != null && messageWrapper.confirm()) {
						messageRepository.store(messageWrapper);
					}
				}
				@Override
				public void onException(Throwable e) {
					log.error(e.getMessage(), e);
				}
			});
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.future.tech.captain.mq.MessageSender#isSynConfirm()
	 */
	@Override
	public boolean isSynConfirm() {
		return false;
	}

}
