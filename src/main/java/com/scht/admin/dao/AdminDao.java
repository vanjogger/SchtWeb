package com.scht.admin.dao;

import com.scht.admin.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/23.
 */
@Repository
public interface AdminDao {
    List listAdminByName(String loginName);

    void updateAdmin(Admin admin);

    List<Admin> listAdmin(@Param("loginName") String loginName, @Param("status") String status, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    Integer countAdmin(@Param("loginName") String loginName, @Param("status") String status);

    void saveAdmin(Admin admin);

    Admin get(String id);

    void delete(String id);

    List<Admin> query(Map<String, Object> map);
}
