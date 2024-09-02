package com.saji.dashboard_backend.secuirty.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {
    private String username;
    private String email;
    private String token;
}
