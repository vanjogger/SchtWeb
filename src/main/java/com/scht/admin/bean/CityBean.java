package com.scht.admin.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/5.
 */
public class CityBean {
    private String no;
    private String name;
    private String level;//1:ʡ��  2������   3������
    private String pid;//0:����
    private List<CityBean> sublist;


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<CityBean> getSublist() {
        return sublist;
    }

    public void setSublist(List<CityBean> sublist) {
        this.sublist = sublist;
    }
}
