package com.express.service;

import com.express.pojo.Message;

public interface MessageService {

	void addMessage(Message message);

	void setHasRead(Integer id, Integer to_id);

}
