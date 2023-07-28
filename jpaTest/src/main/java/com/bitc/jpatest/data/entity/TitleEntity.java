package com.bitc.jpatest.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "jpa_title")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TitleEntity {
//  @Id
//  private int empNo;

  @Id
  private String title;

  @Id
  @Temporal(TemporalType.DATE)
  private Date fromDate;

  @Temporal(TemporalType.DATE)
  private Date toDate;

  @Id
  @ManyToOne
  @ToString.Exclude
  @JoinColumn(name = "emp_no")
  private EmployeesEntity employees;
}
