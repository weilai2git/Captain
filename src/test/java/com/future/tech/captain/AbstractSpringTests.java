package com.future.tech.captain;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/config/spring/applicationContext.xml",
		"classpath:/config/spring/applicationContext-mongo.xml",
		"classpath:/config/spring/applicationContext-rabbitMq.xml",
		"classpath:/config/spring/applicationContext-reliableMessageConfig.xml",
		"classpath:/config/spring/applicationContext-zookeeper.xml",
		"classpath:/config/spring/applicationContext-rocketMq.xml",
		"classpath:/config/spring/applicationContext-activeMq.xml"})
public abstract class AbstractSpringTests {

}
