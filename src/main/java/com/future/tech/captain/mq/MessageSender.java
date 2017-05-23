package com.future.tech.captain.mq;


public interface MessageSender {
	
	boolean send(String correlationId, Object message);
	
	boolean isSynConfirm();
}
