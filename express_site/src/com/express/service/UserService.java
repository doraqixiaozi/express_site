package com.express.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import com.express.pojo.LoginForm;
import com.express.pojo.LoginResult;
import com.express.pojo.SignUpForm;
import com.express.pojo.User;


public interface UserService {

	public void doSignup(SignUpForm user, MultipartFile multfile, HttpServletRequest request) throws DataAccessException, IllegalStateException, IOException;
public LoginResult login(LoginForm loginForm);
public List<String> getSimilarEmail(String email);
public User searchfriend(String email);
public void makeFriends(Integer s_id, Integer f_id);
}
