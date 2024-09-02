package com.saji.dashboard_backend.secuirty.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.saji.dashboard_backend.modules.user_managment.entities.Token;
import com.saji.dashboard_backend.modules.user_managment.entities.User;
import com.saji.dashboard_backend.modules.user_managment.repositories.TokenRepo;
import com.saji.dashboard_backend.secuirty.dtos.SignInRequest;
import com.saji.dashboard_backend.secuirty.dtos.SignInResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final TokenRepo tokenRepo;
    private final JwtService jwtService;

    public SignInResponse signIn(SignInRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        var user = (User) auth.getPrincipal();

        String token = jwtService.generateToken(user);
        SignInResponse response = new SignInResponse();
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        response.setUsername(token);

        revokeAllUserTokens(user);
        saveUserToken(user, token);

        return response;
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepo.findAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepo.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = new Token();
        token.setUser(user);
        token.setToken(jwtToken);

        tokenRepo.save(token);
    }
}
