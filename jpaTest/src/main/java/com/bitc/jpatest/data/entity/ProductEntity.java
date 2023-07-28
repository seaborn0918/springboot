package com.bitc.jpatest.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// callSuper : 부모 클래스의 필드를 해당 클래스에 포함하는 역할을 하는 속성. 롬복에서 제공

@Entity
@Table(name = "jpa_product")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long number; // 상품 번호

  @Column(nullable = false)
  private String name; // 상품 이름

  @Column(nullable = false)
  private Integer price; // 가격

  @Column(nullable = false)
  private Integer stock; // 재고수량

  // 아래의 필드는 부모클래스인 BaseEntity로부터 상속받아 사용
  // @Column(nullable = false)
  // private LocalDateTime createdAt = LocalDateTime.now(); // 등록시간
  //
  // private LocalDateTime updatedAt; // 최종 수정 시간

  // mappedBy 속성은 양방향 매핑이 아니면 사용하지 않아도 됨
  @OneToOne(mappedBy = "product")
  @ToString.Exclude
  private ProductDetailEntity productDetail;

  @ManyToOne
  @JoinColumn(name = "provider_id")
  @ToString.Exclude
  private ProviderEntity provider;
}
