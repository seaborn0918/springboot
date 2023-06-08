package com.bitc.board1.service;

import com.bitc.board1.DTO.AreaDTO;

import java.util.List;

public interface AreaService {
  List<AreaDTO> getDistrictList(String cityName) throws Exception;
  List<AreaDTO> getTownList(String districtName) throws Exception;
}
