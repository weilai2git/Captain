package com.future.tech.captain.service;

import com.future.tech.captain.api.CorrelationData;

public interface ReliableMessageService {
	
	boolean prepare(CorrelationData correlationData, Object message);
	
	void confirm(CorrelationData correlationData);
	
	void cancel(CorrelationData correlationData);
}
