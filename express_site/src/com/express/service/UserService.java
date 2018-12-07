package com.express.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import com.express.pojo.Friend;
import com.express.pojo.LoginForm;
import com.express.pojo.LoginResult;
import com.express.pojo.SignUpForm;
import com.express.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;


public interface UserService {

	public void doSignup(SignUpForm user, MultipartFile avatr, HttpServletRequest request) throws DataAccessException, IllegalStateException, IOException;
public LoginResult login(LoginForm loginForm);
public List<String> getSimilarEmail(String email);
public Friend searchfriend(String email);
public void makeFriends(Integer s_id, Integer f_id) throws JsonProcessingException, IOException;
}
