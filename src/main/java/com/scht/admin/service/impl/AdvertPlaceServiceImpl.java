package com.scht.admin.service.impl;

import com.scht.admin.dao.AdvertDao;
import com.scht.admin.dao.AdvertPlaceDao;
import com.scht.admin.entity.AdvertPlace;
import com.scht.admin.service.AdvertPlaceService;
import com.scht.common.PageInfo;
import com.scht.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/21.
 */
@Service
public class AdvertPlaceServiceImpl implements AdvertPlaceService {
    @Autowired
    AdvertPlaceDao advertPlaceDao;

    @Autowired
    AdvertDao advertDao;
    @Override
    public PageInfo list(String code, PageInfo pageInfo) {
        List<AdvertPlace> list = advertPlaceDao.list(code,pageInfo.getStart(), pageInfo.getLimit());
        int count = advertPlaceDao.count(code);
        pageInfo.setResult(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public int save(AdvertPlace data) {
        if(findByCode(data.getCode()) != null) {
            return -1;
        }
        data.setId(UUIDFactory.random());
        data.setCreateTime(System.currentTimeMillis());
        this.advertPlaceDao.save(data);
        return 0;
    }

    @Override
    public int update(AdvertPlace data) {
        AdvertPlace ap = findByCode(data.getCode());
        if(ap != null && !ap.getId().equals(data.getId())){
            return -1;
        }
        this.advertPlaceDao.update(data);
        return 0;
    }

    @Override
    public AdvertPlace find(String id) {
        return this.advertPlaceDao.find(id);
    }

    @Override
    public AdvertPlace findByCode(String code) {
        return this.advertPlaceDao.findByCode(code);
    }

    @Override
    public int delete(String id) {
        Map<String,Object> map = new HashMap<>();
        map.put("placeId", id);
        if(advertDao.count4Page(map)>0){
            return -1;
        }
        this.advertPlaceDao.delete(id);
        return 0;
    }
}
