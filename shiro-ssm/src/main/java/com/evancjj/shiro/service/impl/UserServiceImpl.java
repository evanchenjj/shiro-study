package com.evancjj.shiro.service.impl;

import com.evancjj.shiro.dao.UserMapper;
import com.evancjj.shiro.pojo.User;
import com.evancjj.shiro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "/userService")
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userDao;
    @Override
    public User getUserByUsername(String username) {
        User user = userDao.getUserByUsername(username);
        return user;
    }

    @Override
    public void insertUser(User user) {
        userDao.insert(user);
    }
}
