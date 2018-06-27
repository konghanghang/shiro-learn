package com.ysla.realm;

import com.ysla.dao.IUserDao;
import com.ysla.vo.User;
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

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WebRealm extends AuthorizingRealm {

    @Resource
    private IUserDao userDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        Set<String> roles = getRolesByUsername(username);
        Set<String> permissions = getPermissionsByUsername(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 模拟从数据库获取权限信息
     * @param username
     * @return
     */
    private Set<String> getPermissionsByUsername(String username) {
        List<String> list = userDao.getPermissionByUsername(username);
        Set<String> set = new HashSet<>(list);
        return set;
    }

    /**
     * 模拟从数据库获取用户角色
     * @param username
     * @return
     */
    private Set<String> getRolesByUsername(String username) {
        List<String> list = userDao.getRoleByUsername(username);
        Set<String> set = new HashSet<>(list);
        return set;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = getPasswordByUsername(username);
        if (password == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,password,getName());
        // 加盐
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));
        return authenticationInfo;
    }

    /**
     * 模拟从数据库按照用户名获取密码
     * @param username
     * @return
     */
    private String getPasswordByUsername(String username) {
        User user = userDao.getUserByUsername(username);
        if(user != null){
            return user.getPassword();
        }
        return null;
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("1234567","test");
        System.out.println(md5Hash.toString());
    }
}
