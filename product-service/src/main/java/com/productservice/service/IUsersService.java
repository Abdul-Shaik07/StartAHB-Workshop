package com.productservice.service;

import com.productservice.dto.LoginRequest;
import com.productservice.dto.UsersRequest;
import com.productservice.response.UsersResponse;

import java.util.Map;

public interface IUsersService {

    UsersResponse registerUsers(UsersRequest usersRequest);
    Map<String, String> login(LoginRequest loginRequest);
}
