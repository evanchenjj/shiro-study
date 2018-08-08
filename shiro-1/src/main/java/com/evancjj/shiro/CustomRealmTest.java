package com.evancjj.shiro;

import com.evancjj.realm.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class CustomRealmTest {


    @Test
    public void customRealmTest(){
//        构建用户自定义realm
        CustomRealm realm = new CustomRealm();
//        创建DefaultSecurityManager对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
//        创建HashedCredentialsMatcher凭证匹配器，启用md5加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        realm.setCredentialsMatcher(matcher);
//
        securityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(securityManager);
//        获取subject对象
        Subject subject =SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("cjj","123456");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(subject.isAuthenticated()+"自定义realm是否认证");
        subject.checkRole("admin");
        subject.checkRole("user");

    }

}
