package com.bitc.secuity.service;

import com.bitc.secuity.data.entity.User;
import com.bitc.secuity.data.reposit.UserReposit;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// UserDetailsService : 스프링 시큐리티에서 제공하는 인터페이스로 사용자 정보를 가져옴
@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
  private final UserReposit userReposit;

  // UserDetailsService 인터페이스에서 제공하는 사용자 정보를 가져오는 추상 메소드
  // userReposit를 통해서 db에 있는 사용자 정보를 가져옴
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    // 정상적으로 데이터를 가져올 경우 해당 데이터 출력
    // 없으면 예외 처리
    User user = userReposit.findByEmail(email).orElseThrow(()->new IllegalArgumentException(email));
    return user;
  }
}
