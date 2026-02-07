package com.productservice.service;

import com.productservice.dto.LoginRequest;
import com.productservice.dto.UsersRequest;
import com.productservice.model.Role;
import com.productservice.model.Users;
import com.productservice.repository.UsersRepository;
import com.productservice.response.UsersResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements IUsersService{

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final ModelMapper modelMapper;
    private final UsersRepository usersRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public UsersResponse registerUsers(UsersRequest usersRequest) {
        Users users = modelMapper.map(usersRequest, Users.class);
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        users.setRole(Role.USER);
        usersRepository.save(users);
        return mapToResponse(users);
    }

    @Override
    public Map<String, String> login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        boolean authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken).isAuthenticated();
        if(!authenticate) {
            throw new RuntimeException("Invalid Credentials!, Please Login with your correct credentials");
        }
        return Map.of("token", jwtService.generateToken(loginRequest.getUsername()));
    }

    private UsersResponse mapToResponse(Users users) {
        return modelMapper.map(users, UsersResponse.class);
    }
}
