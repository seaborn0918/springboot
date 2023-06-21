package com.bitc.xml_json_parser.DTO;

import lombok.Data;

import java.util.List;

@Data
public class BoxOfficeResultDTO {
  private String boxOfficeType;
  private String showRange;
  private List<DailyBoxOfficeDTO> dailyBoxOfficeList = null;
}
