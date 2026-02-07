package com.productservice.response;

import com.productservice.model.Role;
import lombok.Data;

@Data
public class UsersResponse {

    private Long id;
    private String email;
    private String username;
    private Role role;
}
