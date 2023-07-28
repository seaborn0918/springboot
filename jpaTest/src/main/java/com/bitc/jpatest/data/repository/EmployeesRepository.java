package com.bitc.jpatest.data.repository;

import com.bitc.jpatest.data.entity.EmployeesEntity;
import com.bitc.jpatest.data.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeesRepository extends JpaRepository<EmployeesEntity, Integer> {

  Optional<EmployeesEntity> findByEmpNo(int empNo); // 사원번호 검색
  List<EmployeesEntity> findAllByFirstName(String firstName);

  List<EmployeesEntity> findAllByGender(Gender gender); // 해당 성별 모두 출력

  int countByGender(Gender gender); // 해당 성별 사원수
}
