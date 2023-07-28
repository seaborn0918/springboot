package com.bitc.jpatest.controller;

import com.bitc.jpatest.service.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeesController {

  private final EmployeesService employeesService;

  @GetMapping("/")
  public String employees() throws Exception {
    employeesService.find();
    return "success";
  }
}
