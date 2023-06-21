package com.bitc.springboard_ksh.controller;

import com.bitc.springboard_ksh.DTO.UserDTO;
import com.bitc.springboard_ksh.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {
  @Autowired
  private UserService userService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String doLogin() throws Exception {
    return "login/login";
  }

  // 로그인 프로세스
  @RequestMapping(value = "/submit", method = RequestMethod.POST)
  public String doLoginProcess(UserDTO user, HttpServletRequest req) throws Exception {

    int result = userService.isUserInfo(user.getUserId(), user.getUserPw());

    if (result == 1) {
      UserDTO userInfo = userService.getUserInfo(user.getUserId());
      HttpSession session = req.getSession();
      session.setAttribute("userId", userInfo.getUserId());
      session.setAttribute("userName", userInfo.getUserName());
      session.setAttribute("userEmail", userInfo.getUserEmail());
      session.setMaxInactiveInterval(60);

      return "redirect:/login/loginSuccess";
    } else {
      return "redirect:/login/";
    }
  }

   // 로그인 성공
  @RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
  public ModelAndView doLoginSuccess(HttpServletRequest req) throws Exception {
    ModelAndView mv = new ModelAndView("redirect:/board/");

    HttpSession session = req.getSession();
    UserDTO user = new UserDTO();
    user.setUserId((String) session.getAttribute("userId"));
    user.setUserName((String) session.getAttribute("userName"));
    user.setUserEmail((String) session.getAttribute("userEmail"));
    mv.addObject("userInfo", user);

    return mv;
  }
}
