package com.scht.admin.service;

import com.scht.admin.entity.Admin;
import com.scht.common.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/23.
 */
public interface AdminService {

    List listAdminbyName(String loginName);

    void updateAdmin(Admin admin);


    List<Admin> listAdmin(@Param("loginName") String loginName, @Param("status") String status, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

    Integer countAdmin(@Param("loginName") String loginName, @Param("status") String status);

    void saveAdmin(Admin admin);

    Admin get(String id);

    void delete(String id);

    PageInfo listAdmin(String loginName, String status, PageInfo pageInfo);

    List<Admin> query(Map<String, Object> map);
}
