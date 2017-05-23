/**
 * 
 */
package com.future.tech.captain.api;

/**
 * 
 * Title: CorrelationData.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company:  FutureTech<br>
 * 
 * @author weilai
 * May 19, 2017
 */

public class CorrelationData {
	
	private String id;
	
	private String messageSenderName;
	
	private String messageConfirmCheckerName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessageSenderName() {
		return messageSenderName;
	}

	public void setMessageSenderName(String messageSenderName) {
		this.messageSenderName = messageSenderName;
	}

	public String getMessageConfirmCheckerName() {
		return messageConfirmCheckerName;
	}

	public void setMessageConfirmCheckerName(String messageConfirmCheckerName) {
		this.messageConfirmCheckerName = messageConfirmCheckerName;
	}
	
}
