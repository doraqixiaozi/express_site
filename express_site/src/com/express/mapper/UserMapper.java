package com.express.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.express.pojo.LoginForm;
import com.express.pojo.SignUpForm;
import com.express.pojo.User;

public interface UserMapper {
public void addUser(@Param("user") SignUpForm user);
public User selectUserById(Integer id);
public List<User> selectFriendsById(Integer id);
public User login(LoginForm loginForm);
public List<String> getSimilarEmail(String email);
}
