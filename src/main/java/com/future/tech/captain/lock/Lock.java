/**
 * 
 */
package com.future.tech.captain.lock;

import java.util.concurrent.TimeUnit;

/**
 * 
 * Title: Lock.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company:  FutureTech<br>
 * 
 * @author weilai
 * May 23, 2017
 */

public interface Lock {
	
	boolean tryLock(long time, TimeUnit timeUnit);
	
	void lock() throws Exception;
	
	void unlock();
	
}
