/**
 * 
 */
package com.future.tech.captain.config;

import java.util.Map;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.InitializingBean;

import com.future.tech.captain.api.MessageConfirmChecker;
import com.future.tech.captain.mq.MessageSender;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Title: CaptainConfig.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company: FutureTech<br>
 * 
 * @author weilai May 22, 2017
 */
public class CaptainConfig implements InitializingBean{
	
	/**
	 * 消息发送器列表
	 */
	@Setter
	private Map<String, MessageSender> messageSenderHolder;
	
	/**
	 * 消息确认检查列表
	 */
	@Setter
	private Map<String, MessageConfirmChecker> messageConfirmCheckerHolder;
	
	/**
	 * 应用名
	 */
	@Setter
	@Getter
	private String appName;
	
	/**
	 * 每次轮询消息数最大值
	 */
	@Setter
	@Getter
	private int planLimit = 100;
	
	/**
	 * 消息轮询等待时间
	 */
	@Setter
	@Getter
	private int jobWaitSecs = 30;
	
	@Setter
	@Getter
	private CuratorFramework curatorClient;

	public MessageSender findMessageSender(String messageSenderName) {
		return this.messageSenderHolder.get(messageSenderName);
	}

	public MessageConfirmChecker findMessageConfirmChecker(String messageConfirmCheckerName) {
		return this.messageConfirmCheckerHolder.get(messageConfirmCheckerName);
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		if ( messageSenderHolder == null || messageSenderHolder.isEmpty()) {
			throw new IllegalArgumentException("Can not find any message sender!");
		}
		if ( messageConfirmCheckerHolder == null || messageConfirmCheckerHolder.isEmpty() ) {
			throw new IllegalArgumentException("Can not find any message confirm checker!");
		}
	}
}
