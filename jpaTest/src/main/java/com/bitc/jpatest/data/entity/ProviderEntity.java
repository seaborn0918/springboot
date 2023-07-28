package com.bitc.jpatest.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jpa_provider")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProviderEntity extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  // 업체 엔티티와 상품 엔티티의 관계가 1대N 이기 때문에 @OneToMany 어노테이션을 사용함
  // 영속성 : JPA로 관계 설정이 되어 있는 데이터 베이스 사용 시 메인 엔티티의 데이터가 변경될 경우 서브 엔티티의 데이터도 함께 수정되는 것을 말함
  // cascade : 영속성 전이 설정을 위한 속성
  // - ALL : 모든 영속 상태 변경에 대해서 영속성 전이를 사용
  // - PERSIST : 엔티티가 영속화될 때 연관된 에티티도 함께 영속화
  // - MERGE : 엔티티를 영속성 컨텍스트에 병합할 때 연관된 엔티티도 병함
  // - REMOVE : 엔티티를 제거할 때 연관된 엔티티도 함께 제거
  // - REFRESH : 엔티티를 새로고침 할 때 연관된 엔티티도 새로 고침
  // - DETACH : 엔티티를 영속성 컨텍스트에서 제외하면 연관된 엔티티도 제외
  @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
  @ToString.Exclude
  private List<ProductEntity> productList = new ArrayList<>();
}
