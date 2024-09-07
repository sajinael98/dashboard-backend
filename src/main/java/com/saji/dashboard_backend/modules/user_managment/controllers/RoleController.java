package com.saji.dashboard_backend.modules.user_managment.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saji.dashboard_backend.modules.user_managment.dtos.RoleDto;
import com.saji.dashboard_backend.modules.user_managment.dtos.RoleResponse;
import com.saji.dashboard_backend.modules.user_managment.entities.Role;
import com.saji.dashboard_backend.modules.user_managment.services.RoleService;
import com.saji.dashboard_backend.shared.controllers.BaseController;

@RestController
@RequestMapping("/roles")
public class RoleController extends BaseController<Role, RoleDto, RoleResponse> {
    public RoleController(RoleService rolesService) {
        super(rolesService);
    }
}
