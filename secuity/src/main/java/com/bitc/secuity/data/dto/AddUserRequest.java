package com.bitc.secuity.data.dto;

import lombok.Getter;
import lombok.Setter;

// 클라이언트와 통신시 사용하는 데이터
@Getter
@Setter
public class AddUserRequest {
  private String email;
  private String password;
}
