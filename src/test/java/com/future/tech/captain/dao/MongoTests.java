/**
 * 
 */
package com.future.tech.captain.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.future.tech.captain.AbstractSpringTests;
import com.future.tech.captain.api.CorrelationData;
import com.future.tech.captain.domain.MessageWrapper;

/**
 * 
 * Title: MongoTests.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company:  FutureTech<br>
 * 
 * @author weilai
 * May 19, 2017
 */

public class MongoTests extends AbstractSpringTests{
	@Autowired
	private MessageMongodbDAO messageMongodbDAO;
	
	@Test
	public void store() {
		MessageWrapper messageWrapper = new MessageWrapper();
		messageWrapper.setId("1");
		messageWrapper.setMessage("Test");
		messageMongodbDAO.save(messageWrapper);
	}
}
