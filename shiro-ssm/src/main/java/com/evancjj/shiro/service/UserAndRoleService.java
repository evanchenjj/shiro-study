package com.evancjj.shiro.service;

import com.evancjj.shiro.pojo.UserAndRole;

public interface UserAndRoleService {

    UserAndRole getUserAndRoleByUsername(String username);
}
