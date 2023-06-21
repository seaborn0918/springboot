package com.bitc.springboard_ksh.service;

import com.bitc.springboard_ksh.DTO.UserDTO;
import com.bitc.springboard_ksh.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
  @Autowired
  private UserMapper userMapper;
  @Override
  public int isUserInfo(String userId, String userPw) throws Exception {
    return userMapper.isUserInfo(userId, userPw);
  }

  @Override
  public UserDTO getUserInfo(String userId) throws Exception {
    return userMapper.getUserInfo(userId);
  }
}
