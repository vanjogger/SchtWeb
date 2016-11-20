package com.scht.admin.service.impl;

import com.scht.admin.dao.LogDao;
import com.scht.admin.entity.SystemLog;
import com.scht.admin.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/12/31.
 */

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogDao logDao;

    @Override
    public List<SystemLog> listLog(String operateName, Date startDate, Date endDate, int pageNo, int pageSize) {
        long startMills = 0;
        if(startDate!=null)
            startMills = startDate.getTime();
        long endMills = 0;
        if(endDate!=null)
            endMills = endDate.getTime();;
        if(pageNo<1)
            pageNo = 1;
        int pageIndex = (pageNo-1)*pageSize;
        return this.logDao.listLog(operateName,startMills,endMills,pageIndex,pageSize);
    }

    @Override
    public Integer countLog(String operateName, Date startDate, Date endDate) {
        long startMills = 0;
        if(startDate!=null)
            startMills = startDate.getTime();
        long endMills = 0;
        if(endDate!=null)
            endMills = endDate.getTime();;
        return this.logDao.countLog(operateName,startMills,endMills);
    }

    @Override
    public void saveLog(SystemLog log) {
        this.logDao.saveLog(log);
    }
}
