package com.future.tech.captain.repository;

import java.util.List;

import com.future.tech.captain.domain.MessageWrapper;
import com.future.tech.captain.domain.MessageWrapperIdentity;

public interface MessageRepository {
	
	MessageWrapper store(MessageWrapper messageWrapper);
	
	void remove(MessageWrapperIdentity messageWrapperIdentity);
	
	MessageWrapper loadMessage(MessageWrapperIdentity messageWrapperIdentity);
	
	List<MessageWrapper> findAllPreparedByAppName(String appName, int limit);
}
