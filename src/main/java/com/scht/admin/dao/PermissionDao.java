package com.scht.admin.dao;

import com.scht.admin.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2015/12/30.
 */
@Repository
public interface PermissionDao {
    List<Permission> searchByPage(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    int count4Page();

    List<Permission> listAllPermission();

    Permission findPermission(String id);

    void updatePermission(Permission permission);

    void savePermission(Permission permission);

    void deletePermission(String id);
}
