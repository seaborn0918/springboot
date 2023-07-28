package com.bitc.board4.service;

import com.bitc.board4.entity.JpaBoardEntity;
import com.bitc.board4.repository.JpaBoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaBoardServiceImpl implements JpaBoardService {

  // DB에 명령어를 전달하는 인터페이스
  // 마이바티스에서 mapper 인터페이스 및 xml 파일과 동일한 역할을 함
  public final JpaBoardRepository jpaBoardRepository;

  public JpaBoardServiceImpl(JpaBoardRepository jpaBoardRepository) {
    this.jpaBoardRepository = jpaBoardRepository;
  }

  // 게시판 목록 가져오기
  @Override
  public List<JpaBoardEntity> selectBoardList() throws Exception {
    return jpaBoardRepository.findAllByOrderByBoardIdxDesc();
  }

  // 게시판 글 등록하기, 수정하기
  @Override
  public void saveBoard(JpaBoardEntity board) throws Exception {
    // JpaRepository에서 제공하는 save() 메소드를 사용하여 insert와 update 기능을 구현
    jpaBoardRepository.save(board);
  }

  // 게시판 글 상세보기
  @Override
  public JpaBoardEntity selectBoardDetail(long boardIdx) throws Exception {
    // Optional : DB에서 데이터를 조회시 조회된 데이터가 null일 경우 이 null이 자바 언어의 null과 의미가 조금 다름. DB의 null은 특정 컬럼에 데이터가 없다는 의미로 사용되고, 자바의 null 은 객체의 주소가 없다는 의미
    // DB의 null을 자바 소스코드에 그대로 적용 시 원하지 않는 오류가 발생할 수 있음
    // 이러한 오류를 줄이기 위해 만들어진 타입이 Optional 타입
    Optional<JpaBoardEntity> optional = jpaBoardRepository.findById(boardIdx);

    // 조회된 데이터 유무를 알려줌
    if (optional.isPresent()) {
      //반환할 데이터 타입의 객체 생성. get() 메소드를 통해서 데이터를 가져옴
      JpaBoardEntity board = optional.get();
      // 조회수 1 올림
      board.setHitCnt(board.getHitCnt() + 1);
      // 조회수 1 올린 내용을 DB에 저장
      jpaBoardRepository.save(board);
            // 조회된 내용 반환
      return board;

    } else {
      // 데이터 없을 시 강제 예외발생
      throw new NullPointerException();
    }
  }

  // 게시판 글 삭제
  @Override
  public void deleteBoard(long boardIdx) throws Exception {
    // JpaRepository에서 제공하는 deleteById() 메소드를 사용하여 삭제
    jpaBoardRepository.deleteById(boardIdx);
  }
}
