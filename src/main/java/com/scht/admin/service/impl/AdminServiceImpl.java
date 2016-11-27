package com.scht.admin.service.impl;


import com.scht.admin.dao.AdminDao;
import com.scht.admin.entity.Admin;
import com.scht.admin.service.AdminService;
import com.scht.common.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/23.
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;


    @Override
    public List listAdminbyName(String loginName) {
        return this.adminDao.listAdminByName(loginName);
    }

    @Override
    public void updateAdmin(Admin admin) {
        this.adminDao.updateAdmin(admin);
    }

    @Override
    public List<Admin> listAdmin(String loginName, String status, int pageNo, int pageSize) {
        if(pageNo<1)
            pageNo = 1;
        int pageIndex = (pageNo-1)*pageSize;
        return this.adminDao.listAdmin(loginName,status,pageIndex,pageSize);
    }

    @Override
    public Integer countAdmin(String loginName, String status) {
        return this.adminDao.countAdmin(loginName,status);
    }

    @Override
    public void saveAdmin(Admin admin) {
        this.adminDao.saveAdmin(admin);
    }

    @Override
    public Admin get(String id) {
        return this.adminDao.get(id);
    }

    @Override
    public void delete(String id) {
        this.adminDao.delete(id);
    }

    @Override
    public PageInfo listAdmin(String loginName, String status, PageInfo pageInfo) {

        List list = this.adminDao.listAdmin(loginName,status,pageInfo.getStart(),pageInfo.getLimit());
        Integer total = this.adminDao.countAdmin(loginName,status);
        pageInfo.setResult(list);
        pageInfo.setTotal(total);

        return pageInfo;
    }

    @Override
    public List<Admin> query(Map<String, Object> map) {
        return this.adminDao.query(map);
    }
}
