package com.evancjj.shiro.service.impl;

import com.evancjj.shiro.dao.UserAndRoleMapper;
import com.evancjj.shiro.pojo.UserAndRole;
import com.evancjj.shiro.service.UserAndRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "/userAndRoleService")
public class UserAndRoleServiceImpl implements UserAndRoleService {
    @Resource
    private UserAndRoleMapper userAndRoleDao;

    @Override
    public UserAndRole getUserAndRoleByUsername(String username) {
        UserAndRole userAndRole = userAndRoleDao.getUserAndRoleByUsername(username);
        return userAndRole;
    }
}
