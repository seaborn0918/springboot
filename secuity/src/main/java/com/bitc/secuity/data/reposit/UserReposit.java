package com.bitc.secuity.data.reposit;

import com.bitc.secuity.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReposit extends JpaRepository<User, Long> {
  // 사용자 email을 가져오는 쿼리 메소드 생성
  Optional<User> findByEmail(String email);

  // Optional<org.springframework.security.core.userdetails.User> findByEmail(String username);
}
