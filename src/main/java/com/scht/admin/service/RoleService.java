package com.scht.admin.service;


import com.scht.admin.entity.Role;
import com.scht.admin.entity.RolePermission;

import java.util.List;

/**
 * Created by Administrator on 2015/12/28.
 */
public interface RoleService {
    List<String> listPermissionByRoleId(String roleId);

    List<Role> listRole();

    void deleteRolePermissionByPermissionId(String permissionId);

    Role getRolebyId(String id);

    void saveRole(Role role);

    void updateRole(Role role);

    List<Role> getRoleByName(String roleName);

    List<RolePermission> findRolePermission(String roleId);

    void saveRolePermission(RolePermission rp);

    void deleteRolePermission(String roleId);
}
