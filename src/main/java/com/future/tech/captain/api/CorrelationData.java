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

	public String getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "CorrelationData [id=" + id + "]";
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
