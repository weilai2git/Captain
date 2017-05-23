/**
 * 
 */
package com.future.tech.captain.job;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.future.tech.captain.lock.Lock;

import lombok.Setter;

/**
 * 
 * Title: DefaultConfirmMessageScheduleTrigger.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company: FutureTech<br>
 * 
 * @author weilai May 22, 2017
 */
@Component
@EnableScheduling
public class DefaultConfirmMessageScheduleTrigger implements SchedulingConfigurer {

	@Autowired
	private PlanMaker planMaker;

	@Autowired
	private Lock lock;

	@Setter
	private String cron = "0/2 * * * * ?";
	
	@Setter
	private boolean runnable = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.scheduling.annotation.SchedulingConfigurer#
	 * configureTasks(org.springframework.scheduling.config.
	 * ScheduledTaskRegistrar)
	 */
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.addTriggerTask(new Runnable() {
			@Override
			public void run() {
				if ( runnable ) {
					try {
						if (lock.tryLock(2000, TimeUnit.MILLISECONDS)) {
							planMaker.makeAndDoPlan();
						}
					} finally {
						lock.unlock();
					}
				}
			}
		}, new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				CronTrigger trigger = new CronTrigger(cron);
				Date nextExec = trigger.nextExecutionTime(triggerContext);
				return nextExec;
			}
		});
	}

}
