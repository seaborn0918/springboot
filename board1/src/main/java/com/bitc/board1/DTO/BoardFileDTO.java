package com.bitc.board1.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// lombok : 현재 자바 웹 프로젝트에서 필수적으로 사용되는 라이브러리
// getter/setter, toString() 메소드를 자동으로 생성해주는 라이브러리
// @Getter : 해당 클래스에서 사용되고 있는 필드의 getter를 자동으로 생성함
// @Setter : 해당 클래스에서 사용되고 있는 필드의 setter를 자동으로 생성함
// @Data : @Getter, @Setter, @ToString의 기능을 동시에 수행하는 어노테이션
@Data
public class BoardFileDTO {
  private int idx;
  private int boardIdx;
  private String originalFileName;
  private String storedFileName;
  private long fileSize;
}
