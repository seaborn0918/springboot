package com.bitc.securityboard.controller;


import com.bitc.securityboard.data.entity.JpaBoard;
import com.bitc.securityboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
  private final BoardService boardService;


  // 게시물 목록 페이지
  @GetMapping("/")
  public ModelAndView selectBoardList() throws Exception{
    ModelAndView mv = new ModelAndView("board/boardList");
    List<JpaBoard> boardList = boardService.selectBoardList();

    mv.addObject("boardList", boardList);
    return mv;
  }

  @GetMapping("/{boardIdx}")
  public ModelAndView selectBoardDetial(@PathVariable("boardIdx") Long boardIdx) throws Exception{
    ModelAndView mv = new ModelAndView("board/selectBoardDetail");
    JpaBoard board = boardService.selectBoardDetail(boardIdx);

    mv.addObject("board", board);
    return mv;
  }
}
