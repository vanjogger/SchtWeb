package com.scht.admin.service.impl;

import com.scht.admin.dao.ShopTypeDao;
import com.scht.admin.entity.Shop;
import com.scht.admin.entity.ShopType;
import com.scht.admin.service.ShopTypeService;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import com.scht.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wxh on 2016/11/26.
 */
@Service
public class ShopTypeServiceImpl implements ShopTypeService {

    @Autowired
    ShopTypeDao shopTypeDao;

    @Override
    public List<ShopType> listBySort(int sort) {
        return shopTypeDao.listBySort(sort);
    }

    @Override
    public List<ShopType> listMap(Map params) {
        return this.shopTypeDao.listMap(params);
    }

    @Override
    public List<ShopType> listByKey(String key) {
        return this.shopTypeDao.listByKey(key);
    }

    @Override
    public List<ShopType> listAll(Map params) {
        List<ShopType> list = this.shopTypeDao.listMap(params);
        if(StringUtil.isNotEmpty(list)){
            Map map = new HashMap();
            for(ShopType shopType:list){
                map.put("parentId",shopType.getId());
                List<ShopType> subs = this.shopTypeDao.listMap(params);
                shopType.setSubs(subs);
            }
        }
        return list;
    }

    //****************** 接口  *******************
    @Override
    public RetResult list() {
        RetResult result = null;
        try{
            Map map = new HashMap();
            map.put("parentId","0");
            List<ShopType> list = this.shopTypeDao.listMap(map);
            if(StringUtil.isNotEmpty(list)){
                for(ShopType type:list){
                    map.put("parentId",type.getId());
                    List<ShopType> subs = this.shopTypeDao.listMap(map);
                    type.setSubs(subs);
                }
            }
            result = new RetResult(RetResult.RetCode.OK);
            RetData data = new RetData(list);
            result.setData(data);
        }catch (Exception e){
            e.printStackTrace();
            result = new RetResult(RetResult.RetCode.Execute_Error);
        }
        return result;
    }


}
