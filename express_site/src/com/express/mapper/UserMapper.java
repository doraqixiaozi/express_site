package com.express.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.express.pojo.Friend;
import com.express.pojo.LoginForm;
import com.express.pojo.SignUpForm;
import com.express.pojo.User;

public interface UserMapper {
public void addUser(@Param("user") SignUpForm user) throws DataAccessException;
public User selectUserById(Integer id);
public List<Friend> selectFriendsBysId(Integer id);
public User login(LoginForm loginForm);
public List<String> getSimilarEmail(String email);
public List<Friend> selectFriendsByfId(Integer id);
public Friend selectFriendByEmail(String email);
public void makeFriends(@Param("s_id") Integer s_id,@Param("f_id") Integer f_id);
public Friend selectFriendById(Integer id);
}
