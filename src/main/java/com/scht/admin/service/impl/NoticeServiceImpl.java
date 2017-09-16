package com.scht.admin.service.impl;

import com.scht.admin.dao.NoticeDao;
import com.scht.admin.entity.Notice;
import com.scht.admin.service.NoticeService;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wxh on 2016/12/13.
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeDao noticeDao;

    @Override
    public RetResult list(String no) {
        RetResult result = null;
        try{
            List<Notice> list = this.noticeDao.list(no);

            RetData data = new RetData(list);
            result = new RetResult(RetResult.RetCode.OK);
            result.setData(data);
        }catch (Exception e){
            e.printStackTrace();
            result = new RetResult(RetResult.RetCode.Execute_Error);
        }
        return result;
    }

    @Override
    public RetResult detail(String id) {
        RetResult result = null;
        try{
            Notice notice = this.noticeDao.findById(id);
            this.noticeDao.addCount(id);
            RetData data = new RetData(notice);
            result = new RetResult(RetResult.RetCode.OK);
            result.setData(data);
        }catch (Exception e){
            e.printStackTrace();
            result = new RetResult(RetResult.RetCode.Execute_Error);
        }
        return result;
    }
}
