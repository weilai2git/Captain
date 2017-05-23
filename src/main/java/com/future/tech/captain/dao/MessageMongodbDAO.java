/**
 * 
 */
package com.future.tech.captain.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.future.tech.captain.domain.MessageWrapper;

/**
 * 
 * Title: MessageMongodbDAOImpl.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017<br>
 * Company: FutureTech<br>
 * 
 * @author weilai May 19, 2017
 */

public interface MessageMongodbDAO extends MongoRepository<MessageWrapper, String> {
	List<MessageWrapper> findByAppName(String appName, Pageable pageable);
}
