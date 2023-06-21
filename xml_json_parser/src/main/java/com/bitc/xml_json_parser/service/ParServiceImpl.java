package com.bitc.xml_json_parser.service;

import com.bitc.xml_json_parser.DTO.*;
import com.google.gson.Gson;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
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
      if (urlConn != null) urlConn.disconnect();
    }
    return itemList;
  }

  @Override
  public List<DailyBoxOfficeDTO> getDailyBoxOfficeList(String strUrl) throws Exception {
    List<DailyBoxOfficeDTO> itemList = null;
    URL url = null;
    HttpURLConnection urlConn = null;
    // BufferedReader : 파일 혹은 네트워크 통신 시 가져온 데이터를 임시 버퍼에 저장하면서 데이터를 읽는 클래스
    BufferedReader reader = null;

    try {
      url = new URL(strUrl);
      urlConn = (HttpURLConnection) url.openConnection();
      urlConn.setRequestMethod("GET");


      // BufferedReader를 사용하여 HttpURLConnection을 통해서 가져온 데이터를 읽어옴
      // JAXB 라이브러리는 클래스 내부에 네트워크 데이터 처리 부분이 이미 존재함
      // 현재는 JSON 데이터를 가져옴
      // 네트워크 통신 시 사용하는 JSON 데이터는 자바 스크립트의 Object 타입을 흉내낸 문자열
      reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

      // 자바에서 문자열을 표현하기 위한 클래스인 String 클래스는 문자열을 하나의 문자열을 저장하고 해당 문자열에 다른 문자열을 연결할 경우 실제로는 기존 문자열에 다른 문자열이 추가되는 것이 아니라 기존 문자열과 추가된 문자열이 합쳐진 새 문자열을 생성
      // 네트워크 통신을 통해서 들어오는 무자열 데이터를 처리하기에는 기존 String 클래스는 비효율적이므로 StringBuilder 클래스를 사용함
      StringBuilder sb = new StringBuilder(); // 네트워크 데이터로 문자열을 만들기 위한 변수 선언
      String line; // 네트워크에서 가져온 문자열을 저장할 변수

      // 버퍼에 저장된 네트워크 데이터를 데이터가 없을 때까지 반복해서 가져옴
      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }

      // Gson 라이브러리를 사용하여 json 파싱하기 위한 객체 생성
      // json 문자열을 자바의 Object 타입으로 변경
      Gson gson = new Gson();
      // 첫번째 매개변수는 변경할 문자열. 두번째 매개변수는 변경할 자바 클래스 타입
      BoxOfficeDTO boxOffice = gson.fromJson(sb.toString(), BoxOfficeDTO.class);
      itemList = boxOffice.getBoxOfficeResult().getDailyBoxOfficeList();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (reader != null) reader.close();
      if (urlConn != null) urlConn.disconnect();
    }

    return itemList;
  }
}
