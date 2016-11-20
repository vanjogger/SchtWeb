package com.scht.admin.service;


import com.scht.admin.entity.Permission;

import java.util.List;

/**
 * Created by Administrator on 2015/12/30.
 */
public interface PermissionService {
    List<Permission> listPermission(int pageNo, int pageSize);

    int countPermission();

    List<Permission> listAllPermission();

    Permission findPermission(String id);

    void updatePermission(Permission permission);

    void savePermission(Permission permission);

    void deletePermission(String id);
}
