package com.scht.admin.service.impl;

import com.scht.admin.dao.PermissionDao;
import com.scht.admin.entity.Permission;
import com.scht.admin.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/12/30.
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionDao permissionDao;

    @Override
    public List<Permission> listPermission(int pageNo, int pageSize) {
        if(pageNo<1)
            pageNo = 1;
        int pageIndex = (pageNo-1)*pageSize;
       // return this.permissionDao.list(pageIndex,pageSize);
        return null;
    }

    @Override
    public int countPermission() {
        return 0;
    }

    @Override
    public List<Permission> listAllPermission() {
        return this.permissionDao.listAllPermission();
    }

    @Override
    public Permission findPermission(String id) {
        return this.permissionDao.findPermission(id);
    }

    @Override
    public void updatePermission(Permission permission) {
        this.permissionDao.updatePermission(permission);
    }

    @Override
    public void savePermission(Permission permission) {
        this.permissionDao.savePermission(permission);
    }

    @Override
    public void deletePermission(String id) {
        this.permissionDao.deletePermission(id);
    }
}
