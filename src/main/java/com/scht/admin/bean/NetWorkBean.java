package com.scht.admin.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/13.
 */
public class NetWorkBean {

    private int level;
    private int x;//�㼶
    private int y;//�ϼ����
    private int z;//�Լ������

    private String id;
    private String name;
    private String createTime;
    private int total;
    private String type;

    private List<NetWorkBean> list;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<NetWorkBean> getList() {
        return list;
    }

    public void setList(List<NetWorkBean> list) {
        this.list = list;
    }
}
