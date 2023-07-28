package com.bitc.jpatest.data.repository;

import com.bitc.jpatest.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductEntity, Long> {
  // 문제 1) employees DB의 employees, salaries, titles 테이블에 대한 각각의 Entity를 생성하고 관계 설정
  // 현재 testdb에서 생성
  // employees 테이블이 메인 테이블, salaries, titles 테이블은 서브 테이블
  // 참조키 이름은 emp_no
}
