package com.express.service;

import java.util.List;

import com.express.pojo.LoginForm;
import com.express.pojo.LoginResult;
import com.express.pojo.SignUpForm;
import com.express.pojo.User;

public interface UserService {

	public void doSignup(SignUpForm user);
public LoginResult login(LoginForm loginForm);
public List<String> getSimilarEmail(String email);
}
