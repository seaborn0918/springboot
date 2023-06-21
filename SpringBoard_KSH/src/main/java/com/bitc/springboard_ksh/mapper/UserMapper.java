package com.bitc.springboard_ksh.mapper;

import com.bitc.springboard_ksh.DTO.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
  public int isUserInfo(String userId, String userPw) throws Exception;

  UserDTO getUserInfo(String userId) throws Exception;
}
