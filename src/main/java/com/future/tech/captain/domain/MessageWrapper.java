package com.future.tech.captain.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class MessageWrapper {
	@Id
	private String id;
	
	private String appName;
	
	private String messageSenderName;
	
	private String messageConfirmCheckerName;
	
	private Object message;
	
	public MessageWrapperIdentity getIdentity() {
		return new MessageWrapperIdentity(this.id);
	}
}
