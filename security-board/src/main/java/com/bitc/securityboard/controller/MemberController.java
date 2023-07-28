package com.bitc.securityboard.controller;

import com.bitc.securityboard.data.dto.AddMemberReq;
import com.bitc.securityboard.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@Controller
public class MemberController {

  private final MemberService memberService;

  // 회원가입 프로세스. 완료 후 로그인페이지로 이동
  @PostMapping("/member")
  public String signup(AddMemberReq member) throws Exception{
    memberService.join(member);
    return "redirect:/login";
  }

  @GetMapping("/login")
  public String login() throws Exception {
    return "login";
  }

  @GetMapping("/signup")
  public String signup() throws Exception {
    return "signup";
  }

  @GetMapping("/logout")
  public String logout(HttpServletRequest req, HttpServletResponse resp)throws Exception {
    new SecurityContextLogoutHandler().logout(req, resp, SecurityContextHolder.getContext().getAuthentication());
    return "redirect:/login";
  }

}
