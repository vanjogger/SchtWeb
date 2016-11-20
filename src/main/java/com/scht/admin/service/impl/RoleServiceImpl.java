package com.scht.admin.service.impl;


import com.scht.admin.dao.RoleDao;
import com.scht.admin.entity.Role;
import com.scht.admin.entity.RolePermission;
import com.scht.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/12/28.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public List<String> listPermissionByRoleId(String roleId) {
        return this.roleDao.listPermissionByRoleId(roleId);
    }

    @Override
    public List<Role> listRole() {
        return this.roleDao.listRole();
    }

    @Override
    public void deleteRolePermissionByPermissionId(String permissionId) {
        this.roleDao.deleteRolePermissionByPermissionId(permissionId);
    }

    @Override
    public Role getRolebyId(String id) {
        return this.roleDao.getRolebyId(id);
    }

    @Override
    public void saveRole(Role role) {
        this.roleDao.saveRole(role);
    }

    @Override
    public void updateRole(Role role) {
        this.roleDao.updateRole(role);
    }

    @Override
    public List<Role> getRoleByName(String roleName) {
        return this.roleDao.getRoleByName(roleName);
    }

    @Override
    public List<RolePermission> findRolePermission(String roleId) {
        return this.roleDao.findRolePermission(roleId);
    }

    @Override
    public void saveRolePermission(RolePermission rp) {
        this.roleDao.saveRolePermission(rp);
    }

    @Override
    public void deleteRolePermission(String roleId) {
        this.roleDao.deleteRolePermission(roleId);
    }
}
