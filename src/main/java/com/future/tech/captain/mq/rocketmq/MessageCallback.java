/**
 * 
 */
package com.future.tech.captain.mq.rocketmq;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * 
 * Title: MessageCallback.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company:  FutureTech<br>
 * 
 * @author weilai
 * May 23, 2017
 */

public class MessageCallback implements SendCallback{

	/* (non-Javadoc)
	 * @see org.apache.rocketmq.client.producer.SendCallback#onSuccess(org.apache.rocketmq.client.producer.SendResult)
	 */
	@Override
	public void onSuccess(SendResult sendResult) {
		
	}

	/* (non-Javadoc)
	 * @see org.apache.rocketmq.client.producer.SendCallback#onException(java.lang.Throwable)
	 */
	@Override
	public void onException(Throwable paramThrowable) {
		
	}

}
