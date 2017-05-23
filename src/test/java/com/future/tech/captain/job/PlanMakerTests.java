/**
 * 
 */
package com.future.tech.captain.job;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.future.tech.captain.AbstractSpringTests;

/**
 * 
 * Title: PlanMakerTests.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company:  FutureTech<br>
 * 
 * @author weilai
 * May 23, 2017
 */

public class PlanMakerTests extends AbstractSpringTests{
	
	@Autowired
	private PlanMaker planMaker;
	
	@Test
	public void start() {
		this.planMaker.makeAndDoPlan();
	}
}
