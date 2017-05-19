/**
 * 
 */
package com.future.tech.captain.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.future.tech.captain.api.CorrelationData;
import com.future.tech.captain.dao.MessageMongodbDAO;
import com.future.tech.captain.domain.MessageWrapper;
import com.future.tech.captain.repository.MessageRepository;

/**
 * 
 * Title: MessageRepositoryImpl.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company:  FutureTech<br>
 * 
 * @author weilai
 * May 19, 2017
 */
@Repository
public class MessageRepositoryImpl implements MessageRepository {
	
	@Autowired
	private MessageMongodbDAO messageMongodbDAO;
	
	/* (non-Javadoc)
	 * @see com.future.tech.captain.repository.MessageRepository#store(com.future.tech.captain.domain.MessageWrapper)
	 */
	@Override
	public MessageWrapper store(MessageWrapper messageWrapper) {
		return messageMongodbDAO.save(messageWrapper);
	}

	/* (non-Javadoc)
	 * @see com.future.tech.captain.repository.MessageRepository#remove(com.future.tech.captain.api.CorrelationData)
	 */
	@Override
	public void remove(CorrelationData correlationData) {
		messageMongodbDAO.delete(correlationData.getId());
	}

	/* (non-Javadoc)
	 * @see com.future.tech.captain.repository.MessageRepository#loadMessage(com.future.tech.captain.api.CorrelationData)
	 */
	@Override
	public MessageWrapper loadMessage(CorrelationData correlationData) {
		return messageMongodbDAO.findOne(correlationData.getId());
	}

	/* (non-Javadoc)
	 * @see com.future.tech.captain.repository.MessageRepository#findAllMessage(com.future.tech.captain.api.CorrelationData)
	 */
	@Override
	public List<MessageWrapper> findAllMessage(CorrelationData correlationData) {
		return messageMongodbDAO.findAll();
	}

}
