package com.scht.admin.entity;

/**
 * Created by Administrator on 2014/10/10.
 */
public class RolePermission {

    private String id;
    //Admin用户Id
    private String roleId;
    //权限Id
    private String permissionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}
