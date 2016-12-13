package com.scht.admin.service.impl;

import com.scht.admin.bean.Status;
import com.scht.admin.dao.AdvertDao;
import com.scht.admin.service.AdvertService;
import com.scht.front.bean.RestAdvert;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vanjoger on 2016/12/13.
 */
@Service
public class AdvertServiceImpl implements AdvertService {
    @Autowired
    AdvertDao advertDao;


    @Override
    public RetResult list(String code) {
        RetResult result = null;
        try{
            Map map = new HashMap();
            map.put("currentTime",System.currentTimeMillis());
            map.put("status", Status.NORMAL.name());
            map.put("code",code);
            List<RestAdvert> list = this.advertDao.list(map);
            RetData data = new RetData(list);
            result = new RetResult(RetResult.RetCode.OK);
            result.setData(data);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result = new RetResult(RetResult.RetCode.Execute_Error);
        }
        return result;
    }
}
