package com.bitc.secuity.controller;

import com.bitc.secuity.data.dto.AddUserRequest;
import com.bitc.secuity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
  private final UserService userService;

  @PostMapping("/user")
  public String signUp(AddUserRequest user) throws Exception {
    userService.save(user);
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

  @GetMapping
  public String logout(HttpServletRequest req, HttpServletResponse resp) throws Exception{
    new SecurityContextLogoutHandler().logout(req, resp, SecurityContextHolder.getContext().getAuthentication());
    return "redirect:/logout";
  }
}
