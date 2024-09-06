package com.saji.dashboard_backend.modules.user_managment.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saji.dashboard_backend.modules.user_managment.dtos.RoleDto;
import com.saji.dashboard_backend.modules.user_managment.dtos.RoleResponse;
import com.saji.dashboard_backend.modules.user_managment.entities.Role;
import com.saji.dashboard_backend.modules.user_managment.services.RoleService;
import com.saji.dashboard_backend.shared.controllers.BaseController;
import com.saji.dashboard_backend.shared.dtos.ListResponse;

@RestController
@RequestMapping("/roles")
public class RoleController extends BaseController<Role> {
    public RoleController(RoleService rolesService) {
        super(rolesService);
    }

    @PostMapping
    public ResponseEntity<RoleResponse> create(@RequestBody RoleDto request) {
        return super.create(request);
    }

    @GetMapping
    public ResponseEntity<ListResponse<RoleResponse>> getRolesList(@RequestParam Map<String, Object> params) {
        return super.getList(params);
    }

    @GetMapping("/{role-id}")
    private ResponseEntity<RoleResponse> getRoleById(@PathVariable(name = "role-id") Long id) {
        return super.getById(id);
    }

    @PatchMapping("/{role-id}")
    public ResponseEntity<RoleResponse> update(@PathVariable(name = "role-id") Long id, @RequestBody RoleDto request) {
        return super.update(id, request);
    }

    @DeleteMapping("/{role-id}")
    private ResponseEntity<Void> deleteRoleById(@PathVariable(name = "role-id") Long id) {
        return super.deleteById(id);
    }

}
