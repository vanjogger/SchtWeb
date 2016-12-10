package com.scht.admin.service.impl;

import com.scht.admin.dao.NationDao;
import com.scht.admin.entity.Nation;
import com.scht.admin.service.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vanjoger on 2016/12/10.
 */
@Service
public class NationServiceImpl implements NationService {

    @Autowired
    NationDao nationDao;


    @Override
    public List<Nation> listByParentId(String lx,String id) {
        return this.nationDao.listByParentId(lx,id);
    }
}
