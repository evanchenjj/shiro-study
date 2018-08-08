package com.evancjj.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class ShiroAuthenticate {
    /**
     * 构建一个SimpleAccountRealm对象存储信息
     */
    SimpleAccountRealm realm = new SimpleAccountRealm();
    @Before
    public void init(){
        realm.addAccount("cjj","123456");
    }


    @Test
    public void shiroTest(){
        //创建DefaultSecurityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //将realm对象当如DefaultSecurityManager
        securityManager.setRealm(realm);
//        获取SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
//        获取subject
        Subject subject = SecurityUtils.getSubject();
//        创建UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken("cjj","123456");
//        捕获异常
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(subject.isAuthenticated()+"是否认证");

    }

}
