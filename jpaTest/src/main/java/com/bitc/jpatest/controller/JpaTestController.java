package com.bitc.jpatest.controller;

import com.bitc.jpatest.service.EmployeesService;
import com.bitc.jpatest.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class JpaTestController {

  private final ProductService productService;
  private final EmployeesService employeesService;

  @GetMapping("/")
  public String index() throws Exception{
    productService.finds();
    productService.exists();
    productService.count();
    productService.and();
    return "success";
  }

  @GetMapping("/query")
  public String query() throws Exception{
    productService.querySelectAll();
    productService.querySelectName();

    return "query success";
  }

  @GetMapping("/emp")
  public String employees() throws Exception{
    employeesService.getEmployeeMemberInfo(10001);
    employeesService.getEmployeesMemberList("mario");
    return "success";
  }
}
