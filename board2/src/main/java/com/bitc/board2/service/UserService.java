package com.bitc.board2.service;

import com.bitc.board2.DTO.UserDTO;

public interface UserService {
  // 사용자 정보가 있는지 없는지 확인
  public int isUserInfo(String userId, String userPw) throws Exception;

  // 사용자 정보 가져오기
  public UserDTO getUserInfo(String userId) throws Exception;


}
