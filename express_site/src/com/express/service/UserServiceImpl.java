package com.express.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.express.mapper.MessageMapper;
import com.express.mapper.UserMapper;
import com.express.pojo.Friend;
import com.express.pojo.LoginForm;
import com.express.pojo.LoginResult;
import com.express.pojo.Message;
import com.express.pojo.SignUpForm;
import com.express.pojo.User;
import com.express.util.WebUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper usermapper;
	@Resource
	private MessageMapper messagemapper;

	@Override
	public void doSignup(SignUpForm user, MultipartFile multipartFile, HttpServletRequest request)
			throws DataAccessException, IllegalStateException, IOException {		
		String hostpath = request.getServletContext().getRealPath("/profile");
		if (!multipartFile.getContentType().matches("image.+")) {
			throw new IOException();
		}
		String filename = WebUtils.makeFilename(multipartFile.getOriginalFilename());
		String realPath = WebUtils.makeRealPath(filename, hostpath);
		String virtualPath = WebUtils.makeVirtualPath(filename);
		File file = new File(realPath);
		multipartFile.transferTo(file);
		user.setProfile(virtualPath);
		usermapper.addUser(user);
	}

	@Override
	public LoginResult login(LoginForm loginForm) {
		LoginResult result = new LoginResult();
		List<String> errors = new ArrayList<>();
		User user = usermapper.login(loginForm);
		if (user == null || user.getId() == null) {
			errors.add("用户名或密码错误");
			result.setErrors(errors);
			return result;
		}
		List<Friend> friends = usermapper.selectFriendsBysId(user.getId());
		List<Friend> friends2 = usermapper.selectFriendsByfId(user.getId());
		friends.addAll(friends2);
		List<Message> messages = messagemapper.selectNewMessages(user.getId());
		// 为每个朋友添加消息
		if (!friends.isEmpty() && !messages.isEmpty()) {
			for (Friend friend : friends) {
				if (!messages.isEmpty()) {
					List<Message> list = new ArrayList<>();
					for (Message message : messages) {
						/*
						 * if (list==null) { list=new ArrayList<>(); }
						 */
						if (friend.getId() == message.getFrom_id()) {
							list.add(message);
						}
					}
					messages.removeAll(list);
					friend.setMessages(list);
				}
			}
		}
		result.setFriends(friends);
		result.setUser(user);
		return result;
	}

	@Override
	public List<String> getSimilarEmail(String email) {
		if (email == null || email.trim().equals("")) {
			return null;
		}
		return usermapper.getSimilarEmail(email);
	}

	@Override
	public User searchfriend(String email) {
		return usermapper.selectUserByEmail(email);
	}

	@Override
	public void makeFriends(Integer s_id, Integer f_id) {
		usermapper.makeFriends(s_id, f_id);
		System.out.println("sid=" + s_id + " fid=" + f_id);
	}

}
