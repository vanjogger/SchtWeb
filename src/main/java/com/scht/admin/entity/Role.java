package com.scht.admin.entity;

/**
 * Created by Administrator on 2015/12/23.
 */
public class Role {
    //id
    private String id;
    //
    private String roleName;
    //0:正常  1：删除
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
