package com.productservice.service;

import com.productservice.model.Users;
import com.productservice.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users byUsername = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not exists"));

        return new User(byUsername.getUsername(),
                        byUsername.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_" +byUsername.getRole())));
    }
}
