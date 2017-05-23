/**
 * 
 */
package com.future.tech.captain.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.future.tech.captain.AbstractSpringTests;
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
		messageWrapper.setId("myAPP_3");
		messageWrapper.setMessage("Test");
		messageWrapper.setAppName("myAPP");
		messageWrapper.setMessageSenderName("mySender");
		messageWrapper.setMessageConfirmCheckerName("checker");
		messageMongodbDAO.save(messageWrapper);
	}
	
	@Test
	public void findByAppNameAndPage() {
		PageRequest pageRequest = new PageRequest(0, 2);
		List<MessageWrapper> list = messageMongodbDAO.findByAppName("myAPP", pageRequest);
		System.out.println(list);
	}
}
