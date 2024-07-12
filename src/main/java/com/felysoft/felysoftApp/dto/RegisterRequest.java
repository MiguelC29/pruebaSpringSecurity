package com.felysoft.felysoftApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterRequest {

    //private String firstname;
    //private String lastname;
    private String name;
    private String username;
    private String email;
    private String password;
}
