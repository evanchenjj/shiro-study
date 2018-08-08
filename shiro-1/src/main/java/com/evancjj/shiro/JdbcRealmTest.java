package com.evancjj.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class JdbcRealmTest {
    //初始化数据源
    DruidDataSource dataSource = new DruidDataSource();
    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/shiro_study?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
    }

    @Test
    public void jdbcRealmTest() {
        JdbcRealm jdbcRealm = new JdbcRealm();
        String authenticationSql = "select password from user where username = ?";
        jdbcRealm.setAuthenticationQuery(authenticationSql);
        jdbcRealm.setDataSource(dataSource);
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(jdbcRealm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("cjj", "123456");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(subject.isAuthenticated() + "是否认证");

    }
}
