/**
 * 
 */
package com.future.tech.captain.enums;

/**
 * 
 * Title: MessageWrapperStatus.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company:  FutureTech<br>
 * 
 * @author weilai
 * May 23, 2017
 */

public enum MessageWrapperStatus {
	
	PREPARED(1),
	CONFIRMED(2),
	CANCELED(3);
	
	private int code;
	
	private MessageWrapperStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
}
