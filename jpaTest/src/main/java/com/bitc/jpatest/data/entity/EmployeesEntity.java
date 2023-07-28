package com.bitc.jpatest.data.entity;

// 문제1) employees 데이터 베이스를 자신의 mysql server에 import 하고 해당 DB의 본인계정에 사용권한을 설정한 후 entity 및 repository를 작성하고 데이터를 검색하는 프로그램을 작성
// 1. root 게정으로 employees db 생성. import하여 복구
// 2. 일반 계정에 사용 권한 설정
// 3. employees 테이블에 대한 entity 클래스 구현
// 4. repository 인터페이스 생성(쿼리 메소드, @Query)

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "jpa_employees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmployeesEntity {
  @Id
  private int empNo;

  @Column(nullable = false)
  @Temporal(TemporalType.DATE)
  private Date birthDate;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Column(nullable = false)
  @Temporal(TemporalType.DATE)
  private Date hireDate;

  // @ToString.Exclude : 양방향 관계 설정시 ToString을 사용할 경우 상호 참조가 발생함. 상호 참조 방지 위함.
  @OneToMany(mappedBy = "employees")
  @ToString.Exclude
  private List<SalariesEntity> salariesList = new ArrayList<>();

  @OneToMany(mappedBy = "employees")
  @ToString.Exclude
  private List<TitleEntity> titleList = new ArrayList<>();

}




