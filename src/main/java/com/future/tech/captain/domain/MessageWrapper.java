package com.future.tech.captain.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import com.future.tech.captain.enums.MessageWrapperStatus;

import lombok.Data;

@Data
public class MessageWrapper {
	@Id
	private String id;
	
	private String appName;
	
	private String messageSenderName;
	
	private String messageConfirmCheckerName;
	
	private Object message;
	
	private int status;
	
	@Version
	private Long version;
	
	private Date createdDate;
	
	public MessageWrapperIdentity getIdentity() {
		return new MessageWrapperIdentity(this.id);
	}
	
	public boolean isReady2Confirm() {
		return this.status == MessageWrapperStatus.PREPARED.getCode();
	}
	
	public boolean isReady2Cancel() {
		return this.status == MessageWrapperStatus.PREPARED.getCode();
	}
	
	public boolean confirm() {
		if ( this.status == MessageWrapperStatus.PREPARED.getCode() ) {
			this.status = MessageWrapperStatus.CONFIRMED.getCode();
			return true;
		} else {
			return false;
		}
	}
	
	public boolean cancel() {
		if ( this.status == MessageWrapperStatus.PREPARED.getCode() ) {
			this.status = MessageWrapperStatus.CANCELED.getCode();
			return true;
		} else {
			return false;
		}
	}
	
}
