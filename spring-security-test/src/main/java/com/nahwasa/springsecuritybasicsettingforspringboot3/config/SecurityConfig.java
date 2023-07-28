package com.nahwasa.springsecuritybasicsettingforspringboot3.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable().cors().disable()
        .authorizeHttpRequests(req -> req
            .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
            .anyRequest().authenticated())
        .formLogin(login -> login
            .defaultSuccessUrl("/view/dashboard", true)
            .permitAll())
        .logout(Customizer.withDefaults());
    return http.build();
  }
}
