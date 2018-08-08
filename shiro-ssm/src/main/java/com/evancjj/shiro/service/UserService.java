package com.evancjj.shiro.service;

import com.evancjj.shiro.pojo.User;

public interface UserService {
    User getUserByUsername(String username);

    void insertUser(User user);
}
