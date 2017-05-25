/**
 * 
 */
package com.future.tech.captain.lock.zk;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.future.tech.captain.config.CaptainConfig;
import com.future.tech.captain.lock.DistributedLock;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Title: DistributedLockZookeeperImpl.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company: FutureTech<br>
 * 
 * @author weilai May 23, 2017
 */

@Slf4j
@Component("captain.defaultDistributedLock")
public class DistributedLockZookeeperImpl implements DistributedLock {

	@Autowired
	private CaptainConfig config;

	private static final String LOCK_BASE_PATH = "/captain/lock/";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.future.tech.captain.lock.Lock#tryLock()
	 */
	@Override
	public boolean tryLock(long time, TimeUnit timeUnit) {
		InterProcessMutex sharedLock = new InterProcessMutex(config.getCuratorClient(), LOCK_BASE_PATH + config.getAppName());
		try {
			return sharedLock.acquire(time, timeUnit);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.future.tech.captain.lock.Lock#lock()
	 */
	@Override
	public void lock() throws Exception {
		InterProcessMutex sharedLock = new InterProcessMutex(config.getCuratorClient(), LOCK_BASE_PATH + config.getAppName());
		sharedLock.acquire();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.future.tech.captain.lock.Lock#unlock()
	 */
	@Override
	public void unlock() {
		InterProcessMutex sharedLock = new InterProcessMutex(config.getCuratorClient(), LOCK_BASE_PATH + config.getAppName());
		if (sharedLock.isAcquiredInThisProcess()) {
			try {
				sharedLock.release();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
	}

}
