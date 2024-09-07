package com.saji.dashboard_backend.modules.user_managment.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.saji.dashboard_backend.modules.user_managment.dtos.PermissionDto;
import com.saji.dashboard_backend.modules.user_managment.dtos.PermissionResponse;
import com.saji.dashboard_backend.modules.user_managment.dtos.RoleDto;
import com.saji.dashboard_backend.modules.user_managment.dtos.RoleResponse;
import com.saji.dashboard_backend.modules.user_managment.entities.Permission;
import com.saji.dashboard_backend.modules.user_managment.entities.Role;
import com.saji.dashboard_backend.modules.user_managment.mappers.PermissionMapper;
import com.saji.dashboard_backend.modules.user_managment.mappers.RoleMapper;
import com.saji.dashboard_backend.modules.user_managment.repositories.RoleRepo;
import com.saji.dashboard_backend.shared.dtos.ListResponse;
import com.saji.dashboard_backend.shared.services.BaseService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoleService extends BaseService<Role, RoleDto, RoleResponse> {
    private RoleRepo roleRepo;
    private PermissionMapper permissionMapper;

    public RoleService(RoleRepo roleRepo, RoleMapper roleMapper, PermissionMapper permissionMapper) {
        super(roleRepo, roleMapper);
        this.roleRepo = roleRepo;
        this.permissionMapper = permissionMapper;
    }

    public ListResponse<PermissionResponse> createPermission(Long roleId, PermissionDto permissionDto) {
        Role role = getRoleById(roleId);
        Permission permission = permissionMapper.convertRequestToEntity(permissionDto);
        permission.setRole(role);
        List<Permission> permissions = role.getPermissions();
        permissions.add(permission);
        role.setPermissions(permissions);
        roleRepo.save(role);
        List<PermissionResponse> permissionResponses = getPermissionResponseList(role);
        ListResponse<PermissionResponse> response = new ListResponse<>();
        response.setData(permissionResponses);
        response.setTotal((long) permissionResponses.size());
        return response;

    }

    public ListResponse<PermissionResponse> getPermissions(Long roleId) {
        Role role = getRoleById(roleId);
        List<PermissionResponse> permissionResponses = getPermissionResponseList(role);
        ListResponse<PermissionResponse> response = new ListResponse<>();
        response.setData(permissionResponses);
        response.setTotal((long) permissionResponses.size());
        return response;
    }

    private Role getRoleById(Long roleId) {
        return roleRepo.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Resource: roles with id " + roleId + " is not found."));
    }

    private List<PermissionResponse> getPermissionResponseList(Role role) {
        return role.getPermissions().stream()
                .map(p -> (PermissionResponse) permissionMapper.convertEntityToResponse(p)).toList();
    }
}
