package com.bitc.jpatest.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "jpa_product_detail")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDetailEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

//  @Column(nullable = false)
//  private LocalDateTime createdAt = LocalDateTime.now(); // 등록 시간
//
//  private LocalDateTime updatedAt; // 최종 수정 시간

  // @OneToOne : 다른 엔티티 객체를 필드(컬럼)으로 지정할 경우에 1:1 관계로 설정 시 사용하는 어노테이션
  // - mappedBy : @OneToOne 어노테이션 사용시 메인이 되는 Entity에 설정. 양방향 매핑을 할 경우에만 사용
  // - optional : 서브가 되는 엔티티의 OneToOne에 optional = false 사용 시 null 허용하지 않음

  // @OneToMany : 1:N 관계로 설정 시 사용하는 어노테이션
  // @ManyToOne : N:1 관계로 설정 시 사용하는 어노테이션
  // @ManyToMany : N:N 관계로 설정 시 사용하는 어노테이션. 다대다 관계는 프로그래밍 시 거의 사용하지 않음

  // @JoinColumn : 외래키를 설정하는 어노테이션
  // - name : 사용자가 원하는 이름으로 매핑. 생략 시 자동으로 입력됨
  // - referencedColumnName : 외래키가 참조할 상대 테이블의 컬럼명을 지정
  // - foreignKey : 외래키 생성 시 제약조건을 설정(nullable, unique 등)
  @OneToOne(optional = false)
  @JoinColumn(name = "product_number")
  private ProductEntity product;

}
