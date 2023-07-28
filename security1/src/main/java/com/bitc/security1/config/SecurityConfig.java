package com.bitc.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
  // 스프링 시큐리티 사용 시 비밀번호는 반드시 암호화 작업을 진행해야함
  // 스프링 시큐리티에서 제공하는 암호화 라이브러리를 사용하여 비밀번호를 암호화
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // 사용자 설정. 아래에 입력한 리소스는 스프링 시큐리티에서 제외함
  @Bean
  public WebSecurityCustomizer configure() {
    // 무시항목에 등록
    return (web -> web.ignoring()
        .requestMatchers("/static/**"));
    // requestMatchers() : 해당메소드에 지정한 리소스는 스프링 시큐리티의 제어를 받지 않음. (이미지, JS, css등 정적 리소스 제외)
  }

  // 스프링 시큐리티 적용 시 설정
  // 스프링 시큐리티 2.7부터 사용방법이 변경됨
  // SecurityFilterChain을 사용하여 설정을 적용함
  @Bean
  public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
    return http
        // authorizeHttpRequests() : 인증 요청 설정
        // requestMatchers() : 지정한 주소에 대해서 인증 설정
        // permitAll() : 모두 수락
        // hasAnyAuthority() : 지정할 롤에 대해서 사용 인가. 여러개의 인증 정보를 인가할 수 있음
        // hasRoll() : 지정한 롤을 가지고 있는 사용자에 한해서 사용 인가
        // anyRequest() : requestMatchers()에서 지정하지 않은 모든 요청
        // authenticated() : 인증 정보 요구
        // and() : 추가 설정 시 사용
        // formLogin() : 로그인 form 사용
        // loginPage() : 사용자가 생성한 로그인 페이지 사용. 없을 경우 스프링 시큐리티 기본 로그인 화면 사용
        // defaultSuccessUrl() : 로그인 성공시 자동 리다이렉트될 페이지
        // logout() : 로그아웃 페이지 사용
        // logoutSuccessUrl() : 로그아웃 성공시 자동 리다이렉트될 페이지
        .authorizeHttpRequests()
          .requestMatchers("/test/all").permitAll()
          .requestMatchers("/test/member").hasAnyAuthority("USER")
          .requestMatchers("/test/admin").hasRole("ADMIN")
          .anyRequest().authenticated()
        .and()
        .formLogin()
          // .loginPage("/login")
          .defaultSuccessUrl("/test/success")
        .and()
        .logout()
          .logoutSuccessUrl("/login")
          .invalidateHttpSession(true) // 세션 삭제
        .and()
        .csrf().disable().build();
  }

  // 스프링 시큐리티 내에서 사용자 인증을 진행
  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService memoryUserDetailsService) throws Exception {
    return http
        .getSharedObject(AuthenticationManagerBuilder.class)
        // 메모리에 설정한 사용자 정보 혹은 db에서 설정한 사용자 정보
        .userDetailsService(memoryUserDetailsService)
        .passwordEncoder(bCryptPasswordEncoder) // 암호화 방식 설정
        .and()
        .build(); // 객체 생성
  }

  // 서버 메모리 상에 가상으로 사용자 정보를 추가하는 명령
  @Bean
  public InMemoryUserDetailsManager memoryUserDetailsService() {
    // UserDetails : 스프링 시큐리티에서 제공하는 사용자 정보 인터페이스
    UserDetails user = User.builder()
        .username("user")
        // 암호화 모듈을 사용하여 입력한 문자열을 암호화
        .password(bCryptPasswordEncoder().encode("1111"))
        .roles("USER") // 사용자 권한 설정
        .build();
    return new InMemoryUserDetailsManager(user);
  }
}
