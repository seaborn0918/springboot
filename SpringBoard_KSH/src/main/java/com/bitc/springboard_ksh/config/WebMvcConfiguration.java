package com.bitc.springboard_ksh.config;

import com.bitc.springboard_ksh.interceptor.LoginCheck;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//public class WebMvcConfiguration implements WebMvcConfigurer {
//  @Override
//  public void addInterceptors(InterceptorRegistry registry) {
//    registry.addInterceptor(new LoginCheck())
//        .addPathPatterns("/board/*") // 인터셉터가 동작될 컨트롤러 URL 설정
//        .excludePathPatterns("/board/") // 인터셉터 동작에서 제외할 URL 설정
//        .excludePathPatterns("/login/")
//        .excludePathPatterns("/login/logout")
//        .excludePathPatterns("/login/loginFail");
//  }
//}
