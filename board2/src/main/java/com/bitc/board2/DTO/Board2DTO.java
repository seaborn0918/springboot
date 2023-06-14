package com.bitc.board2.DTO;

import lombok.Data;

@Data
public class Board2DTO {
  private int boardIdx;
  private String title;
  private String contents;
  private String createId;
  private String createDt;
  private String updateId;
  private String updateDt;
  private int hitCnt;
}
