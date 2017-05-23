/**
 * 
 */
package com.future.tech.captain.api;

/**
 * 
 * Title: MessageConfirmChecker.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company:  FutureTech<br>
 * 
 * @author weilai
 * May 22, 2017
 */

public interface MessageConfirmChecker {
	boolean isMessageNeed2Send(CorrelationData correlationData);
}
