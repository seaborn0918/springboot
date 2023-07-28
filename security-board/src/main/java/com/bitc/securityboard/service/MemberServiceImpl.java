package com.bitc.securityboard.service;

import com.bitc.securityboard.data.dto.AddMemberReq;
import com.bitc.securityboard.data.entity.Member;
import com.bitc.securityboard.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public void join(AddMemberReq dto) {
    memberRepository.save(Member.builder()
        .email(dto.getEmail())
        .password(bCryptPasswordEncoder.encode(dto.getPassword()))
        .build());
  }
}
