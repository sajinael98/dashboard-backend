package com.saji.dashboard_backend.modules.user_managment.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saji.dashboard_backend.modules.user_managment.entities.Token;

@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
}
