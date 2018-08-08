package com.evancjj.shiro.dao;

import com.evancjj.shiro.pojo.RoleAndPermission;

public interface RoleAndPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleAndPermission record);

    int insertSelective(RoleAndPermission record);

    RoleAndPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleAndPermission record);

    int updateByPrimaryKey(RoleAndPermission record);

    RoleAndPermission getPermissionByRolename(String rolename);
}