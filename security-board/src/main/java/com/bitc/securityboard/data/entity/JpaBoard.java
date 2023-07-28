package com.bitc.securityboard.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "jpa_board")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JpaBoard {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long boardIdx;

  @Column(nullable = false)
  private String title;

  @Column(length = 500, nullable = false)
  private String contents;

  @Column(nullable = false)
  private String createdId;

  @CreatedDate()
  @Column(nullable = false)
  private LocalDateTime createdDt;

  private String updateId;

  @LastModifiedDate
  private LocalDateTime updateDt;

  @Column(nullable = false)
  private int hitCnt;
}
