package com.bitc.board4.service;

import com.bitc.board4.entity.JpaBoardEntity;

import java.util.List;

public interface JpaBoardService {

  List<JpaBoardEntity> selectBoardList() throws Exception;

  void saveBoard(JpaBoardEntity board) throws Exception;

  JpaBoardEntity selectBoardDetail(long boardIdx) throws Exception;

  void deleteBoard(long boardIdx) throws Exception;
}
