package com.future.tech.captain.repository;

import java.util.List;

import com.future.tech.captain.api.CorrelationData;

import com.future.tech.captain.domain.MessageWrapper;

public interface MessageRepository {
	
	MessageWrapper store(MessageWrapper messageWrapper);
	
	void remove(CorrelationData correlationData);
	
	MessageWrapper loadMessage(CorrelationData correlationData);
	
	List<MessageWrapper> findAllMessage(CorrelationData correlationData);
}
