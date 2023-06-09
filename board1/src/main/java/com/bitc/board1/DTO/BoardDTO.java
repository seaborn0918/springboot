package com.bitc.board1.DTO;

import lombok.Data;

import java.util.List;

@Data
public class BoardDTO {
  private int boardIdx;
  private String title;
  private String contents;
  private String createId;
  private String createDt;
  private String updateId;
  private String updateDt;
  private int hitCnt;
  private List<BoardFileDTO> fileList;
}
