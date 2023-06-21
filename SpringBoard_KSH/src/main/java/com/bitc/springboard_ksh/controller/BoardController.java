package com.bitc.springboard_ksh.controller;

import com.bitc.springboard_ksh.DTO.BoardDTO;
import com.bitc.springboard_ksh.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
  @Autowired
  private BoardService boardService;

  // 게시물 목록 보기
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView boardList() throws Exception {
    ModelAndView mv = new ModelAndView("/board/boardList");
    List<BoardDTO> boardList = boardService.selectBoardList();

    mv.addObject("boardList", boardList);
    return mv;
  }

  // 게시물 상세 보기
  @RequestMapping(value = "/{boardIdx}", method = RequestMethod.GET)
  public ModelAndView boardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception {
    ModelAndView mv = new ModelAndView("/board/boardDetail");
    BoardDTO board = boardService.selectBoardDetail(boardIdx);

    mv.addObject("board", board);
    return mv;
  }

  // 목록에서 글쓰기페이지로 이동
  @RequestMapping(value = "/insert", method = RequestMethod.GET)
  public String boardInsertView() throws Exception {
    return "/board/boardWrite";
  }

  // 글쓰기 프로세스
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public String boardInsertProcess(BoardDTO board) throws Exception {
    boardService.insertBoard(board);
    return "redirect:/board/";
  }

  // 상세페이지에서 수정 페이지로 이동
  @RequestMapping(value = "/update/{boardIdx}", method = RequestMethod.GET)
  public ModelAndView boardUpdateView(@PathVariable("boardIdx") int boardIdx) throws Exception {
    ModelAndView mv = new ModelAndView("/board/boardEdit");
    BoardDTO board = boardService.selectBoardDetail(boardIdx);
    mv.addObject("board", board);
    return mv;
  }

  // 수정 프로세스. db에서 업데이트 처리 후 바로 목록 페이지로 리다이렉트
  @RequestMapping(value = "/update/{boardIdx}", method = RequestMethod.PUT)
  public String boardUpdateProcess(BoardDTO board) throws Exception {
    boardService.updateBoard(board);
    return "redirect:/board/";
  }

  // 삭제. db에서 게시글 삭제 처리 후 리다이렉트로 이동
  @RequestMapping(value = "/{boardIdx}", method = RequestMethod.DELETE)
  public String boardDelete(@PathVariable("boardIdx") int boardIdx) throws Exception {
    boardService.deleteBoard(boardIdx);
    return "redirect:/board/";
  }
}
