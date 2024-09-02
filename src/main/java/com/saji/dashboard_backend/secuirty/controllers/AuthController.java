package com.saji.dashboard_backend.secuirty.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saji.dashboard_backend.secuirty.dtos.SignInRequest;
import com.saji.dashboard_backend.secuirty.dtos.SignInResponse;
import com.saji.dashboard_backend.secuirty.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok().body(authService.signIn(request));
    }
}
