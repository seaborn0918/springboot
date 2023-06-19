package com.bitc.xml_json_parser.service;

import com.bitc.xml_json_parser.DTO.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class ParServiceImpl implements ParseService {

  @Override
  public List<PharmacyFullDataItemDTO> getItemListFile(String filePath) throws Exception {
    // jaxb : 자바에서 xml 데이터 파싱을 도와주는 라이브러리
    // jaxb가 자바9부터 자바 기본 라이브러리에서 제외됨
    // Marshal(마샬) : 자바 클래스를 XML 데이터로 변환
    // UnMarshal(언마샬) : XML 데이터를 자바 클래스 타입의 객첼고 변환

    // JAXB 라이브러리 사용 선언
    // PharmacyFullDataDTO 클래스 타입으로 xml 데이터를 파싱
    JAXBContext jc = JAXBContext.newInstance(PharmacyFullDataDTO.class);

    // JAXB 라이브러리를 사용하여 XML 데이터를 자바 클래스 타입의 객체로 변환하는 언마샬 생성
    Unmarshaller um = jc.createUnmarshaller();

    // 기존에 제공된 XML 데이터를 기반으로 PharmacyFullDataDTO 클래스의 객체를 생성하므로 XML 데이터를 파싱하여 가져온 데이터를 PharmacyFullDataDTO 클래스 타입의 객체에 형병환하여 저장
    // unmarshal() : 언마샬을 실행하는 메솓. 매개변수로 파일이나 url을 받음
    PharmacyFullDataDTO fullData = (PharmacyFullDataDTO) um.unmarshal(new File(filePath));

//    PharmacyFullDataHeaderDTO header = fullData.getHeader();

//    PharmacyFullDataBodyDTO body = fullData.getBody();
//    PharmacyFullDataItemsDTO items = body.getItems();
//    List<PharmacyFullDataItemDTO> itemList = items.getItemList();
    List<PharmacyFullDataItemDTO> itemList = fullData.getBody().getItems().getItemList();

    return itemList;
  }

  @Override
  public List<PharmacyFullDataItemDTO> getItemListUrl(String strUrl) throws Exception {
    // 반환할 데이터를 저장할 list 객체
    List<PharmacyFullDataItemDTO> itemList = null;
    // http url 주소를 저장하는 객체
    URL url = null;
    // http 프로토콜을 사용하여 지정된 주소로 통신을 하는 클래스
    HttpURLConnection urlConn = null;

    try {
      url = new URL(strUrl); // 매개변수로 받아온 서비스의 url을 지정
      urlConn = (HttpURLConnection) url.openConnection(); // 지정된 url로 http 연결
      urlConn.setRequestMethod("GET"); // 서버로 통신 방식을 GET 방식으로 설정

      // xml 데이터 파싱을 위한 DTO 클래스 지정
      JAXBContext jc = JAXBContext.newInstance(PharmacyFullDataDTO.class);
      // 언마샬러 객체 생성
      Unmarshaller um = jc.createUnmarshaller();

      // xml 데이터 언마샬 시 url을 사용하여 원격지 서버의 xml 데이터를 파싱함
      PharmacyFullDataDTO fullData = (PharmacyFullDataDTO) um.unmarshal(url);
      itemList = fullData.getBody().getItems().getItemList();

    } catch (JAXBException e) {
      e.printStackTrace();

    } catch (Exception e) {
      e.printStackTrace();

    } finally {
      if(urlConn != null) urlConn.disconnect();
    }
    return itemList;
  }
}
