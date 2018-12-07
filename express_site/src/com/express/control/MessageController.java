package com.express.control;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.express.pojo.User;
import com.express.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 几米 E-mail:862965251@qq.com
 * @version 创建时间：2018年12月2日 上午12:20:23 类说明
 */
@Controller
public class MessageController {
	@Resource
	private MessageService messageService;
	private ObjectMapper mapper = new ObjectMapper();

	// 进入或离开页面会触发使接收人为用户自己的消息为已读
	@RequestMapping("/hasread")
	public @ResponseBody String hasread(@RequestBody String json_id, HttpSession session
			) {
		if (json_id == null || json_id.trim().equals("")) {
			return "please put the id";
		}
		Map readValue;
		try {
			readValue = mapper.readValue(json_id, Map.class);
		} catch (IOException e) {
			e.printStackTrace();
			return "unkown error";
		}
		Integer to_id = (int) readValue.get("id");
		User user = (User) session.getAttribute("user");
		if (user == null || to_id == null) {
			return "unkown error";
		}
		messageService.setHasRead(user.getId(), to_id);
		return "success";
	}
}
