package com.saji.dashboard_backend.modules.user_managment.services;

import org.springframework.stereotype.Service;

import com.saji.dashboard_backend.modules.user_managment.dtos.RoleDto;
import com.saji.dashboard_backend.modules.user_managment.dtos.RoleResponse;
import com.saji.dashboard_backend.modules.user_managment.entities.Role;
import com.saji.dashboard_backend.modules.user_managment.mappers.RoleMapper;
import com.saji.dashboard_backend.modules.user_managment.repositories.RoleRepo;
import com.saji.dashboard_backend.shared.services.BaseService;

@Service
public class RoleService extends BaseService<Role, RoleDto, RoleResponse> {
    public RoleService(RoleRepo roleRepo, RoleMapper roleMapper) {
        super(roleRepo, roleMapper);
    }
}
