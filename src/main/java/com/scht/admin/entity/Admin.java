package com.scht.admin.entity;


import com.scht.util.DateUtil;

/**
 * Created by Administrator on 2014/10/10.
 */
public class Admin {

    private String id;
    //登录账号
    private String loginName;
    //登录密码
    private String password;
    /**
     * 所属角色
     */
    private String roleId;

    private String type;//0:商城管理员  1:代理商

    private String roleName;
    //真实姓名
    private String realName;

    //联系电话
    private String mobile;

    //最后登录时间
    private Long lastLoginTime;
    //登录IP
    private String loginIP;
    //登录次数
    private Integer loginCnt;

    //格式化的登录时间
    private String formatLoginTime;

    /**
     * 状态
     * 0:正常在用
     * 1：已删除
     */
    private String status;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getFormatLoginTime() {
        if(lastLoginTime!=null)
            return DateUtil.getDateFromLong(lastLoginTime);
        return null;
    }

    public void setFormatLoginTime(String formatLoginTime) {
        this.formatLoginTime = formatLoginTime;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public Integer getLoginCnt() {
        if (loginCnt == null)
            loginCnt = 0;
        return loginCnt;
    }

    public void setLoginCnt(Integer loginCnt) {
        this.loginCnt = loginCnt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
