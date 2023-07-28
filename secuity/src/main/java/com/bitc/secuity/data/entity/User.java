package com.bitc.secuity.data.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


// UserDetails : 스프링 시큐리티에서 사용하는 사용자 정보 인터페이스. 사용자 인증이 필요한 경우 무조건 해당 인터페이스를 상속받아 사용해야 함
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private Long id; // pk로 사용하는 순번

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Builder
  public User(String email, String password, String auth) {
    this.email = email;
    this.password = password;
  }

  // 상속받은 UserDetails 인터페이스에서 제공하는 추상 메소드의 구현
  // 스프링 시큐리티에서 사용 권한 인증 받음
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("user"));
  }

  // 사용자 id 가져오기. email을 로그인 id로 사용함
  @Override
  public String getUsername() {
    return email;
  }

  // getPassword() 도 있지만 현재클래스의 필드로 password가 존재하고 @Getter 어노테이션을 통해서 자동으로 getPassword()가 생성 되어 @Override 부분이 빠져 있음

  // 계정 만료 여부 확인. true : 만료되지 않음
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  // 계정 잠금 여부 확인. true : 계정 잠금되지 않음
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  // 비밀번호 만료여부 확인. ture : 비밀번호 사용기한 만료되지 않음
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  // 계정 사용여부 확인. ture : 계정 사용 가능
  @Override
  public boolean isEnabled() {
    return true;
  }
}
