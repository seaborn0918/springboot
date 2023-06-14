package com.bitc.board2.config;

import com.bitc.board2.interceptor.LoginCheck;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
  // 사용자 interceptor 추가
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 인터셉터 파일 설정
    registry.addInterceptor(new LoginCheck())
        .addPathPatterns("/board2/*") // 인터셉터가 동작될 컨트롤러 URL 설정
        .excludePathPatterns("/board2/board2List.do") // 인터셉터 동작에서 제외할 URL 설정
        .excludePathPatterns("/login/login.do")
        .excludePathPatterns("/login/logout.do")
        .excludePathPatterns("/login/loginFail.do");
  }
}
