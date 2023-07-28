package com.bitc.board4.controller;

import com.bitc.board4.entity.JpaBoardEntity;
import com.bitc.board4.service.JpaBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/jpa")
public class BoardController {

  private final JpaBoardService jpaBoardService;

  public BoardController(JpaBoardService jpaBoardService) {
    this.jpaBoardService = jpaBoardService;
  }

  @GetMapping("/")
  public String index() throws Exception {
    return "index";
  }

  // 게시판 목록 페이지
  @GetMapping("/board")
  public ModelAndView selectBoardList() throws Exception {
    ModelAndView mv = new ModelAndView("board/jpaBoardList");

    // 데이터 리스트를 DB 테이블을 나타내는 entity 클래스 통해서 가져옴
    // Service 객체는 마이바티스 사용시와 동일한 역할
    List<JpaBoardEntity> boardList = jpaBoardService.selectBoardList();

    mv.addObject("boardList", boardList);
    return mv;
  }

  // 게시판 글쓰기 페이지 (View)
  @GetMapping("/board/write")
  public String insertBoardView() throws Exception {
    return "board/jpaInsertBoardView";
  }

  // 게시판 글쓰기 페이지(Process)
  @PostMapping("/board/write")
  public String insertBoardProcess(JpaBoardEntity board) throws Exception {
    jpaBoardService.saveBoard(board);
    return "redirect:/jpa/board";
  }

  // 게시판 상세 글 보기
  @GetMapping("/board/{boardIdx}")
  public ModelAndView selectBoardDetail(@PathVariable("boardIdx") long boardIdx) throws Exception {
    ModelAndView mv = new ModelAndView("board/jpaSelectBoardDetail");
    JpaBoardEntity board = jpaBoardService.selectBoardDetail(boardIdx);

    mv.addObject("board", board);
    return mv;
  }

  // 게시판 글 수정
  @PutMapping("/board/{boardIdx}")
  public String updateBoard(JpaBoardEntity board) throws Exception {
    jpaBoardService.saveBoard(board);
    return "redirect:/jpa/board";
  }

  // 게시판 글 삭제
  @DeleteMapping("/board/{boardIdx}")
  public String deleteBoard(@PathVariable("boardIdx") long boardIdx) throws Exception {
    jpaBoardService.deleteBoard(boardIdx);
    return "redirect:/jpa/board";
  }
}
