package com.scht.admin.service;

import com.scht.admin.entity.Admin;
import com.scht.admin.entity.Shop;
import com.scht.front.bean.RetResult;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by wxh on 2016/11/26.
 */
public interface ShopService {
    List<Shop> listByAccount(String account);

    String save(Shop data, Admin admin);

    //根据ids查询商家列表
    List<Shop> listByIds(String[] strings);

    //根据名称查询列表
    List<Shop> listByName(String name, String agentId, String wb);

    Integer countShop(Map params);

    RetResult restLogin(String account, String password);

    RetResult updateInfo(String id, String linkName, String linkMobile, String linkAddress);

    RetResult updatePwd(String id, String oldPwd, String newPwd);

    RetResult list(String name,String shopTypeKey,String sortType,String type,String code, int pageNo, int pageSize,
                   String region,String wb);

    RetResult juliList(String code,String lat,String lng,String name, String shopTypeKey, String type, int pageNo, int pageSize,
                       String region,String wb);
}
