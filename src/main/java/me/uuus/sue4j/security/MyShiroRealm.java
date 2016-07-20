package me.uuus.sue4j.security;


import me.uuus.sue4j.dao.PermissionMapper;
import me.uuus.sue4j.dao.RoleMapper;
import me.uuus.sue4j.dao.UserMapper;
import me.uuus.sue4j.model.Permission;
import me.uuus.sue4j.model.Role;
import me.uuus.sue4j.model.User;
import me.uuus.sue4j.model.UserExample;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author Mr.Su[swb0917@gmail.com]
 */

public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(MyShiroRealm.class);

    @Resource
    private UserMapper userDao;

    @Resource
    private RoleMapper roleDao;

    @Resource
    private PermissionMapper permissionDao;


    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        String username = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        //查出是否有此用户
        final User user = userDao.authentication(new User(username, password));
        if (user == null) {
            throw new AuthenticationException("用户名或密码错误.");
        }
        log.info("----------------用户{}认证成功啦-----------------", username);
        return new SimpleAuthenticationInfo(username, password, getName());
    }


    /**
     * 权限认证，为当前登录的Subject授予角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
        String loginName = (String) super.getAvailablePrincipal(principalCollection);
        //到数据库查是否有此对象
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(loginName);
        List<User> users = userDao.selectByExample(example);// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        if (users != null) {
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用户的角色集合
            final List<Role> roles = roleDao.selectRolesByUserId(users.get(0).getId());
            for (Role role : roles) {
                info.addRole(role.getRoleSign());
                final List<Permission> permissions =  permissionDao.selectPermissionsByRoleId(role.getId());
                for (Permission permission : permissions) {
                    info.addStringPermission(permission.getPermissionSign());
                }
            }
            log.info("----------用户{}查询完成,拥有角色{},拥有权限{}---------", loginName, String.valueOf(info.getRoles()), String.valueOf(info.getStringPermissions()));
            return info;
        }
        return null;
    }

    /**
     * 清楚缓存
     */
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}

