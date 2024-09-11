package com.saji.dashboard_backend.modules.user_managment.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.saji.dashboard_backend.modules.user_managment.dtos.UserDto;
import com.saji.dashboard_backend.modules.user_managment.dtos.UserResponse;
import com.saji.dashboard_backend.modules.user_managment.entities.User;
import com.saji.dashboard_backend.shared.dtos.BaseRequest;
import com.saji.dashboard_backend.shared.dtos.BaseResponse;
import com.saji.dashboard_backend.shared.mappers.BaseMapper;

@Service
public class UserMapper implements BaseMapper<User> {

    @Override
    public BaseResponse convertEntityToResponse(User entity) {
        UserResponse res = new UserResponse();
        BeanUtils.copyProperties(entity, res);
        return res;
    }

    @Override
    public User convertRequestToEntity(BaseRequest req) {
        User user = new User();
        BeanUtils.copyProperties(req, user);
        return user;
    }

}
