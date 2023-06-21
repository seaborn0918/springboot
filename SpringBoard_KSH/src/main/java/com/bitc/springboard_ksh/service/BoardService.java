package com.bitc.springboard_ksh.service;

import com.bitc.springboard_ksh.DTO.BoardDTO;

import java.util.List;

public interface BoardService {
  // 목록
  List<BoardDTO> selectBoardList() throws Exception;

  // 상세
  BoardDTO selectBoardDetail(int boardIdx) throws Exception;

  // 글쓰기
  void insertBoard(BoardDTO board) throws Exception;

  // 수정
  void updateBoard(BoardDTO board) throws Exception;

  // 삭제
  void deleteBoard(int boardIdx) throws Exception;

}
