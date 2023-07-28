package com.bitc.jpatest.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "jpa_salaries")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SalariesEntity {
//  @Id
//  private int empNo;

  @Column(nullable = false)
  private int salary;

  @Id
  @Temporal(TemporalType.DATE)
  private Date fromDate;

  @Column(nullable = false)
  @Temporal(TemporalType.DATE)
  private Date toDate;

  @Id
  @ManyToOne(optional = false)
  @JoinColumn(name = "emp_no")
  @ToString.Exclude
  private EmployeesEntity employees;
}
