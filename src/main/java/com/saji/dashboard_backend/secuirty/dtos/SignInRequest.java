package com.saji.dashboard_backend.secuirty.dtos;

import lombok.Getter;

@Getter
public class SignInRequest {
    private String username;
    private String password;
}
