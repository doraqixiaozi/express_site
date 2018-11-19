package com.express.mapper;

import java.util.List;

import com.express.pojo.Message;

public interface MessageMapper {
public List<Message> selectNewMessages(Integer id);
}
