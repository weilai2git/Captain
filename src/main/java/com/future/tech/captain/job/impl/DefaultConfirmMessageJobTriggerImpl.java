/**
 * 
 */
package com.future.tech.captain.job.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.future.tech.captain.job.ConfirmMessageJobTrigger;
import com.future.tech.captain.job.PlanMaker;

/**
 * 
 * Title: DefaultConfirmMessageJobTriggerImpl.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company:  FutureTech<br>
 * 
 * @author weilai
 * May 22, 2017
 */

public class DefaultConfirmMessageJobTriggerImpl implements ConfirmMessageJobTrigger{
	
	@Autowired
	@Qualifier("defaultPlanMaker")
	private PlanMaker planMaker;

	/* (non-Javadoc)
	 * @see com.future.tech.captain.job.ConfirmMessageJobTrigger#trigger()
	 */
	@Override
	public void start() {
		planMaker.makeAndDoPlan();
	}

}
