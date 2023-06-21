package com.bitc.springboard_ksh.service;

import com.bitc.springboard_ksh.DTO.BoardDTO;
import com.bitc.springboard_ksh.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
  @Autowired
  private BoardMapper boardMapper;

  @Override
  public List<BoardDTO> selectBoardList() throws Exception {
    return boardMapper.selectBoardList();
  }

  @Override
  public BoardDTO selectBoardDetail(int boardIdx) throws Exception {
    boardMapper.updateHitCount(boardIdx);
    return boardMapper.selectBoardDetail(boardIdx);
  }

  @Override
  public void insertBoard(BoardDTO board) throws Exception {
    boardMapper.insertBoard(board);
  }

  @Override
  public void updateBoard(BoardDTO board) throws Exception {
    boardMapper.updateBoard(board);
  }

  @Override
  public void deleteBoard(int boardIdx) throws Exception {
    boardMapper.deleteBoard(boardIdx);
  }
}
