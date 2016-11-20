package com.scht.admin.entity;

/**
 * Created by Administrator on 2014/10/10.
 */
public class Permission {

    private String id;
    //菜单名称
    private String name;
    //菜单地址
    private String url;
    //父级菜单Id
    private String pId;
    //是否被选中  0:否  1：是
    private String isChecked;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }
}
