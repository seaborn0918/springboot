package com.bitc.board3.DTO;

import lombok.Data;

@Data
public class AddressDTO {
  private int addressId;
  private String address;
  private String address2;
  private String district;
  private int cityId;
  private String postalCode;
  private String phone;
  private String lastUpdate;
}
