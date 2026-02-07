package com.productservice.controller;

import com.productservice.dto.LoginRequest;
import com.productservice.dto.UsersRequest;
import com.productservice.response.UsersResponse;
import com.productservice.service.IUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth/api")
@RequiredArgsConstructor
public class UsersController {

    private final IUsersService iUsersService;

    @PostMapping("/registerUsers")
    public ResponseEntity<UsersResponse> register(@RequestBody UsersRequest usersRequest) {
        return ResponseEntity.ok(iUsersService.registerUsers(usersRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(iUsersService.login(loginRequest));
    }

}
