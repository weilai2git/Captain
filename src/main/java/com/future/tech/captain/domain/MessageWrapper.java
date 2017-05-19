package com.future.tech.captain.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class MessageWrapper {
	@Id
	private String id;
	
	private Object message;
}
