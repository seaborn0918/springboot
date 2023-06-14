package com.bitc.board2.service;

import com.bitc.board2.DTO.Board2DTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface Board2Service {
  // 게시판 글 목록보기
  List<Board2DTO> selectBoardList() throws Exception;

  // 게시판 글 상세보기
  Board2DTO selectBoardDetail(int boardIdx) throws Exception;

  // 게시판 글 등록
  // 업로드된 파일 정보 확인을 위한 MultipartHttpServletRequest 타입의 매개변수 추가
  void insertBoard(Board2DTO board, MultipartHttpServletRequest multipart) throws Exception;

  // 게시판 글 수정
  void updateBoard(Board2DTO board) throws Exception;

  // 게시판 글 삭제
  void deleteBoard(int boardIdx) throws Exception;
}
