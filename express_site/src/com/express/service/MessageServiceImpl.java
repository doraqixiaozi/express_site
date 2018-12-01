package com.express.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.express.mapper.MessageMapper;
import com.express.pojo.Message;


@Service
public class MessageServiceImpl implements MessageService {
	@Resource
	MessageMapper messagemapper;

	@Override
	public void addMessage(Message message) {
		messagemapper.addMessage(message);
	}

	@Override
	public void setHasRead(Integer id, Integer to_id) {
		messagemapper.setHasRead(id,to_id);
		
	}

}
