package com.saji.dashboard_backend.modules.user_managment.services;

import org.springframework.stereotype.Service;

import com.saji.dashboard_backend.modules.user_managment.dtos.UserDto;
import com.saji.dashboard_backend.modules.user_managment.entities.User;
import com.saji.dashboard_backend.modules.user_managment.mappers.UserMapper;
import com.saji.dashboard_backend.modules.user_managment.repositories.UserRepo;
import com.saji.dashboard_backend.shared.services.BaseService;

@Service
public class UserService extends BaseService<User, UserDto, UserDto> {
    private UserRepo userRepo;

    public UserService(UserRepo userRepo, UserMapper userMapper) {
        super(userRepo, userMapper);
        this.userRepo = userRepo;
    }

    @Override
    public void validate(User object) {
        if (object.getId() == null) {
            if (userRepo.existsByEmail(object.getEmail())) {
                throw new IllegalArgumentException("email: " + object.getEmail() + " is already taken.");
            }
        } else {
            if (userRepo.findIdByEmail(object.getEmail()) != object.getId()) {
                throw new IllegalArgumentException("email: " + object.getEmail() + " is already taken.");
            }
        }
    }

}
