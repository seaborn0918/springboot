package com.bitc.secuity.service;

import com.bitc.secuity.data.dto.AddUserRequest;
import com.bitc.secuity.data.entity.User;
import com.bitc.secuity.data.reposit.UserReposit;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
  private final UserReposit userReposit;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public Long save(AddUserRequest dto) {
    return userReposit.save(User.builder()
        .email(dto.getEmail())
        .password(bCryptPasswordEncoder.encode(dto.getPassword()))
        .build()).getId();
  }
}
