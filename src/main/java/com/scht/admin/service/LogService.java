package com.scht.admin.service;


import com.scht.admin.entity.SystemLog;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/12/31.
 */
public interface LogService {

    List<SystemLog> listLog(String operateName, Date startDate, Date endDate, int pageNo, int pageSize);

    Integer countLog(String operateName, Date startDate, Date endDate);

    void saveLog(SystemLog log);

}
