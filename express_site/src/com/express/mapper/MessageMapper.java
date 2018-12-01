package com.express.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.express.pojo.Message;

public interface MessageMapper {
public List<Message> selectNewMessages(Integer id);

public void addMessage(@Param("message") Message message);

public void setHasRead(@Param("id") Integer id,@Param("to_id") Integer to_id);
}
