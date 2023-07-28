package com.bitc.securityboard.service;

import com.bitc.securityboard.data.dto.AddMemberReq;

public interface MemberService {
  void join(AddMemberReq dto);
}
