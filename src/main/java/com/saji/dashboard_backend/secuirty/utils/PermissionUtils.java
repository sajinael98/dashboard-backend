package com.saji.dashboard_backend.secuirty.utils;

import com.saji.dashboard_backend.modules.user_managment.entities.User;

public class PermissionUtils {
    public static boolean hasPermission(User user, String entity, String action) {
        String permission = action.toUpperCase() + "_" + entity.toUpperCase();
        return user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(permission));
    }

}
