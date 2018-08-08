package com.evancjj.shiro.controller;

import com.evancjj.shiro.pojo.User;
import com.evancjj.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;


@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    UserService userService;


    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public void registerUser(String username,String password){
        Md5Hash md5HashPassword = new Md5Hash(password,username,1);
        User user = new User();
        user.setUsername(username);
        user.setPassword(md5HashPassword.toString());
        user.setSalt(username);
        userService.insertUser(user);
    }



    @RequestMapping(value = "/subLogin" ,method = RequestMethod.POST)
    public String login(String username,String password,boolean rememberMe){
        Subject subject =SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        token.setRememberMe(rememberMe);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }
        return "redirect:/success.jsp";
    }
}
