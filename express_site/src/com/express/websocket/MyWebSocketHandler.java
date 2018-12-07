package com.express.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import com.express.mapper.UserMapper;
import com.express.pojo.Friend;
import com.express.pojo.Message;
import com.express.pojo.User;
import com.express.service.MessageService;
import com.express.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

@Component
public class MyWebSocketHandler implements WebSocketHandler {

	@Resource
	private MessageService messageService;
	@Resource
	private UserMapper userMapper;
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	// 当MyWebSocketHandler类被加载时就会创建该Map，随类而生
	public static final Map<Integer, WebSocketSession> userSocketSessionMap;

	static {
		userSocketSessionMap = new HashMap<Integer, WebSocketSession>();
	}

	// 握手实现连接后
	public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
		int uid = (Integer) webSocketSession.getAttributes().get("uid");
		if (userSocketSessionMap.get(uid) == null) {
			userSocketSessionMap.put(uid, webSocketSession);
		}
	}

	// 发送信息前的处理
	public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage)
			throws Exception {
		String msg = webSocketMessage.getPayload().toString();
		if (webSocketMessage.getPayloadLength() == 0)
			return;
		// 得到Socket通道中的数据并转化为Message对象
		Message message = objectMapper.readValue(msg, Message.class);
		if (message == null) {
			System.out.println("非法消息：" + webSocketMessage.getPayload().toString());
			return;
		}
		Date date = new Date();
		message.setM_time(date);
		message.setIsread("n");
		// 将信息保存至数据库
		messageService.addMessage(message);
		// 发送Socket信息
		sendMessageToUser(message.getTo_id(), new TextMessage(objectMapper.writeValueAsString(message)));
	}

	public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

	}

	/**
	 * 在此刷新页面就相当于断开WebSocket连接,原本在静态变量userSocketSessionMap中的
	 * WebSocketSession会变成关闭状态(close)，但是刷新后的第二次连接服务器创建的
	 * 新WebSocketSession(open状态)又不会加入到userSocketSessionMap中,所以这样就无法发送消息
	 * 因此应当在关闭连接这个切面增加去除userSocketSessionMap中当前处于close状态的WebSocketSession，
	 * 让新创建的WebSocketSession(open状态)可以加入到userSocketSessionMap中
	 * 
	 * @param webSocketSession
	 * @param closeStatus
	 * @throws Exception
	 */
	public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus){
		try {
			System.out.println("WebSocket:" + webSocketSession.getAttributes().get("uid") + "close connection");
		/*	Iterator<Map.Entry<Integer, WebSocketSession>> iterator = userSocketSessionMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<Integer, WebSocketSession> entry = iterator.next();
				if (entry.getValue().getAttributes().get("uid") == webSocketSession.getAttributes().get("uid")) {
					userSocketSessionMap.remove(webSocketSession.getAttributes().get("uid"));
					System.out.println("WebSocket in staticMap:" + webSocketSession.getAttributes().get("uid") + "removed");
				}
			}*/
			Set<Entry<Integer, WebSocketSession>>  w_set= userSocketSessionMap.entrySet();
			int uid=0;
			for (Entry<Integer, WebSocketSession> entry : w_set) {
				if (entry.getKey()==webSocketSession.getAttributes().get("uid")) {
					uid=entry.getKey();					
					break;
				}
			}
			userSocketSessionMap.remove(uid);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("出错");
		}
		
	}

	public boolean supportsPartialMessages() {
		return false;
	}

	// 发送信息的实现
	public void sendMessageToUser(int uid, TextMessage message) throws IOException {
		WebSocketSession session = userSocketSessionMap.get(uid);
		if (session != null && session.isOpen()) {
			session.sendMessage(message);
		}
	}
	
	public void sendUserToFriends(int friendId,int userid) throws JsonProcessingException, IOException {
		WebSocketSession session = userSocketSessionMap.get(friendId);
		Friend friend = userMapper.selectFriendById(userid);
		friend.setMessages(new ArrayList<Message>());
		if (session != null && session.isOpen()) {
			session.sendMessage(new TextMessage(objectMapper.writeValueAsString(friend)));
		}
	}
	
	public void hello(int friendId,int userid) throws JsonProcessingException, IOException {
		Message message = new Message(null, friendId, userid, "Hello,我们已经是朋友了,快来打个招呼吧", "n", new Date());
		Message message2 = new Message(null, userid, friendId, "Hello,我们已经是朋友了,快来打个招呼吧", "n", new Date());
		WebSocketSession u_session = userSocketSessionMap.get(userid);
		WebSocketSession f_session = userSocketSessionMap.get(friendId);
		if (f_session != null && f_session.isOpen()) {
			f_session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
			messageService.addMessage(message);
		}
		if (u_session != null && u_session.isOpen()) {
			u_session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message2)));
			messageService.addMessage(message2);
		}		
	}
}