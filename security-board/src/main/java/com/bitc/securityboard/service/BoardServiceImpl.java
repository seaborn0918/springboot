package com.bitc.securityboard.service;

import com.bitc.securityboard.data.entity.JpaBoard;
import com.bitc.securityboard.data.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
  private final BoardRepository boardRepository;

  @Override
  public List<JpaBoard> selectBoardList() throws Exception {
    return boardRepository.findAllByOrderByBoardIdxDesc();
  }

  @Override
  public JpaBoard selectBoardDetail(Long boardIdx) throws Exception {
    Optional<JpaBoard> optional = boardRepository.findById(boardIdx);

    if (optional.isPresent()) {
      JpaBoard board = optional.get();
      board.setHitCnt(board.getHitCnt() + 1);
      boardRepository.save(board);
      return board;
    } else {
      throw new NullPointerException();
    }
  }
}
