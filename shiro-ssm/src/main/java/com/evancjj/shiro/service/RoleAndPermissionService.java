package com.evancjj.shiro.service;

import com.evancjj.shiro.pojo.RoleAndPermission;

public interface RoleAndPermissionService {
    RoleAndPermission getPermissionByRolename(String rolename);
}
