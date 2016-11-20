package com.scht.admin.interceptor;

import com.scht.admin.SystemCache;
import com.scht.admin.bean.LoginType;
import com.scht.admin.bean.UserStatus;
import com.scht.admin.entity.Admin;
import com.scht.admin.service.AdminService;
import com.scht.admin.service.BaseService;
import com.scht.admin.service.RoleService;
import com.scht.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/5.
 */
public class RealmDBService extends AuthorizingRealm implements Serializable {


    @Autowired
    AdminService adminService;
    @Autowired
    RoleService roleMenuService;
    @Autowired
    BaseService baseService;


    /**
     * 为当前登录的Subject授予角色和权限
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String loginName = (String) super.getAvailablePrincipal(principalCollection);
        List<String> permissionList = new ArrayList<>();
        //权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        List list = adminService.listAdminbyName(loginName);
        Admin admin = null;
        if (StringUtil.isNotEmpty(list)) {
            admin = (Admin) list.get(0);
            //根据用户名查询其对应的权限列表
            permissionList = this.roleMenuService.listPermissionByRoleId(admin.getRoleId());
            simpleAuthorizationInfo.addStringPermissions(permissionList);
        }

        return simpleAuthorizationInfo;
    }

    /**
     * 验证方法   用户登录时调用
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String key = token.getUsername();
        String[] karrays = key.split("\\|");
        String userName = karrays[0];
        String role = karrays[1];
        if(role.equals(LoginType.Admin.name())) {
            List list = adminService.listAdminbyName(userName);
            Admin admin = null;
            if (StringUtil.isNotEmpty(list)) {
                admin = (Admin) list.get(0);
                if (admin.getStatus().equals(UserStatus.FROZEN.name()))
                    throw new LockedAccountException("账号被锁定");
                AuthenticationInfo autoInfo = new SimpleAuthenticationInfo(admin.getLoginName(), admin.getPassword(), admin.getRoleId());
                setSession(SystemCache.SESSION_ADMIN, admin);
                return autoInfo;
            }
        }else if(role.equals(LoginType.Member.name())){
            /* Member member = baseService.findById(MemberDao.class,userName);
                if(member!=null) {
                    if (member.getStatus().equals(UserStatus.AUDIT.name()))
                        throw new LockedAccountException("账号待审核");
                    if(member.getStatus().equals(UserStatus.FROZEN.name()))
                        throw new LockedAccountException("账号被冻结");
                    AuthenticationInfo autoInfo = new SimpleAuthenticationInfo(member.getName(), member.getLoginPwd(), "member");
                    setSession(SystemCache.SESSION_ADMIN, member);
                    return autoInfo;
          }*/
        }
        return null;
    }


    /**
     * 将登录用户信息放在session中
     *
     */
    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }
}
