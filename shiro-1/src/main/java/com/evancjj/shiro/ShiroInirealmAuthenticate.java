package com.evancjj.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class ShiroInirealmAuthenticate {
    @Test
    public void shiroTest() {
        //创建DefaultSecurityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //将realm对象当如DefaultSecurityManager
        //创建IniRealm对象
        IniRealm iniRealm = new IniRealm("classpath:init.ini");
        securityManager.setRealm(iniRealm);
//        获取SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
//        获取subject
        Subject subject = SecurityUtils.getSubject();
//        创建UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken("cjj", "123456");
//        捕获异常
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(subject.isAuthenticated() + "是否认证");
        subject.checkRole("admin");
        subject.checkPermission("user:delete");
    }
}
