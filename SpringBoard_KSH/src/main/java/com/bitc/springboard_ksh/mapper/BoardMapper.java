package com.bitc.springboard_ksh.mapper;

import com.bitc.springboard_ksh.DTO.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
  List<BoardDTO> selectBoardList() throws Exception;

  BoardDTO selectBoardDetail(int boardIdx) throws Exception;

  void updateHitCount(int boardIdx) throws Exception;

  void insertBoard(BoardDTO board) throws Exception;

  void updateBoard(BoardDTO board) throws Exception;

  void deleteBoard(int boardIdx) throws Exception;

}
