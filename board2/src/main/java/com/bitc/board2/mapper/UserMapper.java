package com.bitc.board2.mapper;

import com.bitc.board2.DTO.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
  public int isUserInfo(String userId, String userPw) throws Exception;

  public UserDTO getUserInfo(String userId) throws Exception;
}
