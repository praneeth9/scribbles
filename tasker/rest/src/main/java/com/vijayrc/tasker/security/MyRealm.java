package com.vijayrc.tasker.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm {
    private static Logger log = LogManager.getLogger(MyCredentialsMatcher.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        check(principals, "principal-collection method argument cannot be null.");
        String username = (String) principals.getPrimaryPrincipal();
        log.info("called "+username);
        return new SimpleAuthorizationInfo(Safe.getRoles(username));
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        String username = upToken.getUsername();
        check(username, "null usernames are not allowed by this realm.");

        String password = Safe.getPassword(username);
        log.info("user entry|" + username);
        check(password, "no account found for user [" + username + "]");

        return new SimpleAuthenticationInfo(username, password.toCharArray(), getName());
    }

    private void check(Object reference, String message) {
        if (reference == null)
            throw new AuthenticationException(message);
    }
}