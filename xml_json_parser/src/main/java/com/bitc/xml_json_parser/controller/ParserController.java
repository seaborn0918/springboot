package com.bitc.xml_json_parser.controller;

import com.bitc.xml_json_parser.DTO.DailyBoxOfficeDTO;
import com.bitc.xml_json_parser.DTO.PharmacyFullDataItemDTO;
import com.bitc.xml_json_parser.DTO.PharmacyFullDataItemsDTO;
import com.bitc.xml_json_parser.service.ParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/parse")
public class ParserController {
  @Autowired
  private ParseService parseService;

  @Value("${full505.kobis.json.DailyBoxOfficeResultUrl}")
  private String serviceUrl;

  @Value("${full505.kobis.json.key}")
  private String serviceKey;

  @RequestMapping("/")
  public String index() throws Exception {
    return "index";
  }

  @GetMapping("/pharmacy/fullDataFile")
  public ModelAndView getFullDataFile() throws Exception {
    ModelAndView mv = new ModelAndView("pharmacy/fullDataFile");

    List<PharmacyFullDataItemDTO> itemList = parseService.getItemListFile("C:\\smart505\\pharmacy.xml");
    mv.addObject("itemList", itemList);
    return mv;
  }

  @GetMapping("/pharmacy/fullDataUrl")
  public String getFullDataUrl() throws Exception {
    return "pharmacy/fullDataUrl";
  }

  @ResponseBody
  @PostMapping("/pharmacy/fullDataUrl")
  public Object getFullDataUrl(@RequestParam("pageNo") String pageNo, @RequestParam("numOfRows") String numOfRows) throws Exception {

    // http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown?serviceKey=xzRt%2BRb3XI2CQmzFl9H1S%2Fv0wxvDePBIFCskDxrSuPnkyc60EgtNtTgoJhUC9peAJbIpjT6JDNC1jhUpAxcTUA%3D%3D&pageNo=1&numOfRows=10

    String serviceUrl = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown";
    String servicekey = "?serviceKey=";
    String key = "xzRt%2BRb3XI2CQmzFl9H1S%2Fv0wxvDePBIFCskDxrSuPnkyc60EgtNtTgoJhUC9peAJbIpjT6JDNC1jhUpAxcTUA%3D%3D";
    String opt1 = "&pageNo=";
    String opt2 = "&numOfRows=";

    List<PharmacyFullDataItemDTO> itemList = parseService.getItemListUrl(serviceUrl + servicekey + key + opt1 + pageNo + opt2 + numOfRows);

    return itemList;
  }

  // 영화진흥원 일일 박스오피스 출력 View
  @GetMapping("/kobis/dailyBoxOffice")
  public String dailyBoxOfficeView() throws Exception {
    return "kobis/dailyBoxOffice";
  }

  // 영화진흥원 일일 박스오피스 데이터 가져오기
  @ResponseBody
  @PostMapping("/kobis/dailyBoxOffice")
  public Object getDailyBoxOfficeProcess(@RequestParam("targetDt") String targetDt) throws Exception {
    // String url = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=" + targetDt;
    String url = serviceUrl + "?key=" + serviceKey + "&targetDt=" + targetDt;

    List<DailyBoxOfficeDTO> dailyBoxOfficeDTOList = parseService.getDailyBoxOfficeList(url);
    return dailyBoxOfficeDTOList;
  }
}
