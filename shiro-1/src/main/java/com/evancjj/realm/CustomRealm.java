package com.evancjj.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {
    /**
     * 重写授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roleSet = getRoleByUsername(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roleSet);

        return info;
    }

    /**
     * 模拟从数据库获取角色信息
     * @param username
     * @return
     */
    private Set<String> getRoleByUsername(String username) {
        Set<String> roleSet = new HashSet<>();
        roleSet.add("admin");
        roleSet.add("user");
        return roleSet;

    }

    /**
     * 重写认证方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = getPasswordByUsername(username);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password,this.getName());
        //盐值加密
        info.setCredentialsSalt(ByteSource.Util.bytes(username));

        return info;
    }

    /**
     * 模拟从数据库获取密码
     * @param username
     * @return
     */
    private String getPasswordByUsername(String username) {
        Map<String,String> userMap = new HashMap<>();
        userMap.put("cjj","7c7d4110a3e9e0ad73072d1b9d145708");
        return userMap.get(username);

    }
    public static void main(String[] args){
        Md5Hash hash= new Md5Hash("123456");
        System.out.println(hash);
    }
}
