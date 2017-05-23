/**
 * 
 */
package com.future.tech.captain.config;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.future.tech.captain.api.MessageConfirmChecker;
import com.future.tech.captain.mq.MessageSender;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Title: CaptainConfig.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company: FutureTech<br>
 * 
 * @author weilai May 22, 2017
 */
public class CaptainConfig {
	@Setter
	private Map<String, MessageSender> messageSenderHolder;
	@Setter
	private Map<String, MessageConfirmChecker> messageConfirmCheckerHolder;
	@Setter
	@Getter
	private String appName;
	
	@Setter
	@Getter
	private int planLimit;

	public MessageSender findMessageSender(String messageSenderName) {
		return this.messageSenderHolder.get(messageSenderName);
	}

	public MessageConfirmChecker findMessageConfirmChecker(String messageConfirmCheckerName) {
		return this.messageConfirmCheckerHolder.get(messageConfirmCheckerName);
	}
}
