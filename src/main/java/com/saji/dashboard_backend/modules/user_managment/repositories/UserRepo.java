package com.saji.dashboard_backend.modules.user_managment.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.saji.dashboard_backend.modules.user_managment.entities.User;
import com.saji.dashboard_backend.shared.repositories.BaseRepository;

@Repository
public interface UserRepo extends BaseRepository<User, Long> {
    Optional<User> findByEmailOrUsername(String email, String username);

    boolean existsByEmail(String email);

    @Query("SELECT u.id FROM User u WHERE u.email = :email")
    Long findIdByEmail(@Param("email") String email);
}
