package com.express.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import com.express.pojo.Message;
import com.express.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

@Component
public class MyWebSocketHandler implements WebSocketHandler {

	@Resource
	private MessageService messageService;
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
	public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
		System.out.println("WebSocket:" + webSocketSession.getAttributes().get("uid") + "close connection");
		Iterator<Map.Entry<Integer, WebSocketSession>> iterator = userSocketSessionMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, WebSocketSession> entry = iterator.next();
			if (entry.getValue().getAttributes().get("uid") == webSocketSession.getAttributes().get("uid")) {
				userSocketSessionMap.remove(webSocketSession.getAttributes().get("uid"));
				System.out.println("WebSocket in staticMap:" + webSocketSession.getAttributes().get("uid") + "removed");
			}
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
}