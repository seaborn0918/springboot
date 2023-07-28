package com.bitc.jpatest.data.dto;

import com.bitc.jpatest.data.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesDto {
  private int empNo;
  private String empName;
  private Gender gender;
}
