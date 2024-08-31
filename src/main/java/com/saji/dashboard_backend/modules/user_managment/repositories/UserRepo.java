package com.saji.dashboard_backend.modules.user_managment.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saji.dashboard_backend.modules.user_managment.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmailOrUsername(String email, String username);
}
