package com.scht.admin.entity;


import com.scht.util.DateUtil;

/**
 * Created by Administrator on 2015/12/31.
 */
public class SystemLog {
    private String id;
    //操作人姓名
    private String operateName;
    //日志内容
    private String operateContent;
    //日志时间
    private Long logDate;
    //当前操作ip
    private String ip;

    private String date;

    public String getDate() {
        if(logDate!=null){
            return DateUtil.getDateFromLong(logDate);
        }
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public String getOperateContent() {
        return operateContent;
    }

    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent;
    }

    public Long getLogDate() {
        return logDate;
    }

    public void setLogDate(Long logDate) {
        this.logDate = logDate;
    }
}
