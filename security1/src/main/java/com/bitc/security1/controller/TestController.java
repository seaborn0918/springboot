package com.bitc.security1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
  @RequestMapping(value = "/all")
  public String exAll() {
    return "exAll";
  }

  @RequestMapping("/admin")
  public String exAdmin() {
    return "exAdmin";
  }

  @RequestMapping("/member")
  public String exMember() {
    return "exMember";
  }

  @RequestMapping("/success")
  public String exSuccess() {
    return "exMember";
  }
}

