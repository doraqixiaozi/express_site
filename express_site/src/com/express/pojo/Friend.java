package com.express.pojo;
/**
* @author 几米 E-mail:862965251@qq.com
* @version 创建时间：2018年11月22日 下午5:07:21
* 类说明
*/

import java.util.List;

public class Friend extends User {
private List<Message> messages;

public List<Message> getMessages() {
	return messages;
}

public void setMessages(List<Message> messages) {
	this.messages = messages;
}

@Override
public String toString() {
	super.toString();
	return "Friend [messages=" + messages + "]";
}
}
