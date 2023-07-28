package com.bitc.securityboard.service;

import com.bitc.securityboard.data.entity.JpaBoard;

import java.util.List;

public interface BoardService {
  List<JpaBoard> selectBoardList() throws Exception;

  JpaBoard selectBoardDetail(Long boardIdx) throws Exception;
}
