package com.evancjj.shiro.service.impl;

import com.evancjj.shiro.dao.RoleAndPermissionMapper;
import com.evancjj.shiro.pojo.RoleAndPermission;
import com.evancjj.shiro.service.RoleAndPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "/roleAndPermissionService")
public class RoleAndPermissionServiceImpl implements RoleAndPermissionService {
    @Resource
    private RoleAndPermissionMapper roleAndPermissionDao;

    public RoleAndPermission getPermissionByRolename(String rolename){
        return roleAndPermissionDao.getPermissionByRolename(rolename);
    }
}
