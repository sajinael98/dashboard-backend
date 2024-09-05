package com.saji.dashboard_backend.modules.user_managment.services;

import org.springframework.stereotype.Service;

import com.saji.dashboard_backend.modules.user_managment.entities.Role;
import com.saji.dashboard_backend.modules.user_managment.mappers.RoleMapper;
import com.saji.dashboard_backend.modules.user_managment.repositories.RoleRepo;
import com.saji.dashboard_backend.shared.services.BaseService;

@Service
public class RolesService extends BaseService<Role>{
    private final RoleMapper roleMapper;
    private final RoleRepo roleRepo;
    
    public RolesService(RoleRepo roleRepo, RoleMapper roleMapper) {
        super(roleRepo, roleMapper);
        this.roleMapper = roleMapper;
        this.roleRepo = roleRepo;
    }
}
