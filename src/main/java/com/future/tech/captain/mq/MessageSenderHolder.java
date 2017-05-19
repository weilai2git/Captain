/**
 * 
 */
package com.future.tech.captain.mq;

import java.util.Map;

import lombok.Setter;

/**
 * 
 * Title: MessageSenderHolder.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company:  FutureTech<br>
 * 
 * @author weilai
 * May 19, 2017
 */

public class MessageSenderHolder {
	
	@Setter
	private Map<String, MessageSender> holder;
	
	public MessageSender findMessageSender(String qName) {
		return holder.get(qName);
	}
}
