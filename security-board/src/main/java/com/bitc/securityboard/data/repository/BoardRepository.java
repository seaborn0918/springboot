package com.bitc.securityboard.data.repository;

import com.bitc.securityboard.data.entity.JpaBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<JpaBoard, Long> {
  List<JpaBoard> findAllByOrderByBoardIdxDesc();
}
