package com.bitc.secuity.service;

import com.bitc.secuity.data.dto.AddUserRequest;

public interface UserService {
  Long save(AddUserRequest dto);
}
