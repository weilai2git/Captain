/**
 * 
 */
package com.future.tech.captain.domain;

import lombok.Getter;

/**
 * 
 * Title: MessageWrapperIdentity.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company: FutureTech<br>
 * 
 * @author weilai May 22, 2017
 */

public class MessageWrapperIdentity {
	
	@Getter
	private String id;

	public MessageWrapperIdentity(String appName, String correlationId) {
		this.id = appName + "_" + correlationId;
	}
	
	public MessageWrapperIdentity(String id) {
		this.id = id;
	}

	public String getOriginId() {
		return id.split("_")[1];
	}
}
