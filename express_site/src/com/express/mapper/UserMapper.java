package com.express.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.express.pojo.User;

public interface UserMapper {
public void addUser(@Param("user") User user);
public User selectUserById(Integer id);
public List<User> selectFriendsById(Integer id);
}
