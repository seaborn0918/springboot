package com.bitc.board1.service;

import com.bitc.board1.DTO.BoardDTO;

import java.util.List;

public interface BoardService {
  // 게시판 글 목록보기
  List<BoardDTO> selectBoardList() throws Exception;

  // 게시판 글 상세보기
  BoardDTO selectBoardDetail(int boardIdx) throws Exception;

  // 게시판 글 등록
  void insertBoard(BoardDTO board) throws Exception;

  // 게시판 글 수정
  void updateBoard(BoardDTO board) throws Exception;

  // 게시판 글 삭제
  void deleteBoard(int boardIdx) throws Exception;
}
