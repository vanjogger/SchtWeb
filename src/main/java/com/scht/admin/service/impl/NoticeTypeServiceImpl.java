package com.scht.admin.service.impl;

import com.scht.admin.dao.NoticeTypeDao;
import com.scht.admin.entity.NoticeType;
import com.scht.admin.service.NoticeTypeService;
import com.scht.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */
@Service
public class NoticeTypeServiceImpl implements NoticeTypeService {

    @Autowired
    NoticeTypeDao noticeTypeDao;
    @Override
    public void insert(NoticeType type) {
        type.setId(UUIDFactory.random());
        type.setCreateTime(System.currentTimeMillis());
        type.setNo(createNo());
        noticeTypeDao.insert(type);
    }

    @Override
    public void update(NoticeType type) {

    }

    private String createNo(){
        List<NoticeType> list = noticeTypeDao.listOrderByNo();
        if(list == null || list.size() == 0) return "01";
        int i =0;
        for(; i < list.size(); i++) {
            NoticeType type = list.get(i);
            if((i+1) != Integer.parseInt(type.getNo())){
                return String.format("%02d",(i+1));
            }
        }
        return String.format("%02d", i+1);
    }
}
