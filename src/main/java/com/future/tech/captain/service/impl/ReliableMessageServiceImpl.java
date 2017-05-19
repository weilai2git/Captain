/**
 * 
 */
package com.future.tech.captain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.future.tech.captain.api.CorrelationData;
import com.future.tech.captain.domain.MessageWrapper;
import com.future.tech.captain.mq.MessageSender;
import com.future.tech.captain.repository.MessageRepository;
import com.future.tech.captain.service.ReliableMessageService;

/**
 * 
 * Title: ReliableMessageServiceImpl.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company:  FutureTech<br>
 * 
 * @author weilai
 * May 19, 2017
 */
@Service
public class ReliableMessageServiceImpl implements ReliableMessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private MessageSender messageSender;

	/* (non-Javadoc)
	 * @see com.future.tech.captain.service.ReliableMessageService#prepare(com.future.tech.captain.api.CorrelationData, java.lang.Object)
	 */
	@Override
	public void prepare(CorrelationData correlationData, Object message) {
		MessageWrapper messageWrapper = new MessageWrapper();
		messageWrapper.setId(correlationData.getId());
		messageWrapper.setMessage(message);
		messageRepository.store(messageWrapper);
	}

	/* (non-Javadoc)
	 * @see com.future.tech.captain.service.ReliableMessageService#confirm(com.future.tech.captain.api.CorrelationData)
	 */
	@Override
	public void confirm(CorrelationData correlationData) {
		MessageWrapper messageWrapper = messageRepository.loadMessage(correlationData);
		messageSender.send(messageWrapper.getMessage());
	}

	/* (non-Javadoc)
	 * @see com.future.tech.captain.service.ReliableMessageService#cancel(com.future.tech.captain.api.CorrelationData)
	 */
	@Override
	public void cancel(CorrelationData correlationData) {
		messageRepository.remove(correlationData);
	}

}
