package com.express.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.express.mapper.MessageMapper;
import com.express.mapper.UserMapper;
import com.express.pojo.LoginForm;
import com.express.pojo.LoginResult;
import com.express.pojo.Message;
import com.express.pojo.SignUpForm;
import com.express.pojo.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper usermapper;
    @Resource
	private MessageMapper messagemapper;
	@Override
	public void doSignup(SignUpForm user) {
    usermapper.addUser(user);
	}
	@Override
	public LoginResult login(LoginForm loginForm) {		
		LoginResult result=new LoginResult();
		List<String> errors=new ArrayList<>();
	    User user = usermapper.login(loginForm);
	    if (user==null || user.getId()==null) {
	    	errors.add("用户名或密码错误");
	    	result.setErrors(errors);
			return result;
		}
	    List<User> friends = usermapper.selectFriendsById(user.getId());
	    List<Message> messages = messagemapper.selectNewMessages(user.getId());
	    result.setFriends(friends);
	    result.setMessages(messages);
	    result.setUser(user);
		return result;
	}
	@Override
	public List<String> getSimilarEmail(String email) {
		
		return usermapper.getSimilarEmail(email);
	}

}
