package com.bitc.board2.mapper;

import com.bitc.board2.DTO.Board2DTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Board2Mapper {
  List<Board2DTO> selectBoardList() throws Exception;

  Board2DTO selectBoardDetail(int boardIdx) throws Exception;

  void insertBoard(Board2DTO board) throws Exception;

  void updateBoard(Board2DTO board) throws Exception;

  void deleteBoard(@Param("idx") int boardIdx) throws Exception;

  void updateHitCount(int boardIdx) throws Exception;
}

