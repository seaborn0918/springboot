package com.bitc.springboard_ksh.service;

import com.bitc.springboard_ksh.DTO.UserDTO;

public interface UserService {

  int isUserInfo(String userId, String userPw) throws Exception;

  UserDTO getUserInfo(String userId) throws Exception;
}
