package com.bitc.securityboard.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMemberReq {
  private String email;
  private String password;
}
