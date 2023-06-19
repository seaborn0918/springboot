package com.bitc.board3.service;

import com.bitc.board3.DTO.AddressDTO;
import com.github.pagehelper.Page;

public interface AddressService {

  Page<AddressDTO> getAddressList(int pageNum) throws Exception;
}
