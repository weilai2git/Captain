/**
 * 
 */
package com.future.tech.captain.repository.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.future.tech.captain.dao.MessageMongodbDAO;
import com.future.tech.captain.domain.MessageWrapper;
import com.future.tech.captain.domain.MessageWrapperIdentity;
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
public class MessageRepositoryMongoDBImpl implements MessageRepository {
	
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
	public void remove(MessageWrapperIdentity messageWrapperIdentity) {
		messageMongodbDAO.delete(messageWrapperIdentity.getId());
	}

	/* (non-Javadoc)
	 * @see com.future.tech.captain.repository.MessageRepository#loadMessage(com.future.tech.captain.api.CorrelationData)
	 */
	@Override
	public MessageWrapper loadMessage(MessageWrapperIdentity messageWrapperIdentity) {
		return messageMongodbDAO.findOne(messageWrapperIdentity.getId());
	}

	/* (non-Javadoc)
	 * @see com.future.tech.captain.repository.MessageRepository#findByAppName(java.lang.String)
	 */
	@Override
	public List<MessageWrapper> findByAppName(String appName, int limit) {
		PageRequest pageRequest = new PageRequest(0, limit);
		return messageMongodbDAO.findByAppName(appName, pageRequest);
	}
	
}
