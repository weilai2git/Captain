/**
 * 
 */
package com.future.tech.captain.service;

import org.springframework.stereotype.Component;

import com.future.tech.captain.api.CorrelationData;
import com.future.tech.captain.api.MessageConfirmChecker;

/**
 * 
 * Title: DefaultMessageConfirmCheckerImpl.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company:  FutureTech<br>
 * 
 * @author weilai
 * May 22, 2017
 */
@Component("defaultChecker")
public class DefaultMessageConfirmCheckerImpl implements MessageConfirmChecker {

	/* (non-Javadoc)
	 * @see com.future.tech.captain.api.MessageConfirmChecker#isMessageNeed2Send(com.future.tech.captain.api.CorrelationData)
	 */
	@Override
	public boolean isMessageNeed2Send(CorrelationData correlationData) {
		return true;
	}

}
