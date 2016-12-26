package com.scht.admin.service.impl;

import com.scht.admin.bean.Status;
import com.scht.admin.dao.*;
import com.scht.admin.entity.*;
import com.scht.admin.service.ShopService;
import com.scht.front.bean.RestShop;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import com.scht.util.MD5Util;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by vanjoger on 2016/11/26.
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopDao shopDao;
    @Autowired
    BaseMyBatisDao baseMyBatisDao;
    @Autowired
    ShopMoneyDao shopMoneyDao;
@Autowired
    ShopTypeDao shopTypeDao;
    @Autowired
    ProductDao productDao;

    @Override
    public List<Shop> listByAccount(String account) {
        return this.shopDao.listByAccount(account);
    }

    @Override
    public String  save(Shop data, Admin admin) {
        try {
            List<Shop> list = this.shopDao.listByAccount(data.getAccount());
            if (StringUtil.isNotNull(data.getId())) {
                if (!list.isEmpty()) {
                    Shop shop = list.get(0);
                    if (!shop.getId().equals(data.getId()))
                        return "-1";
                }
                this.baseMyBatisDao.update(ShopDao.class, data);

                List<ShopMoney> moneys = this.shopMoneyDao.listByShopId(data.getId());
                if(!moneys.isEmpty()){
                    ShopMoney money = moneys.get(0);
                    money.setShopName(data.getName());
                    this.baseMyBatisDao.update(ShopMoneyDao.class,money);
                }else{
                    saveShopMoney(data);
                }
                return "0";
            } else {
                if (!list.isEmpty())
                    return "-1";

                data.setId(UUIDFactory.random());
                data.setStatus(Status.NORMAL.name());
                data.setCreateTime(new Date().getTime());
                data.setPassword(MD5Util.getMD5ofStr(data.getPassword()));
                data.setAgentId(admin.getId());
                this.baseMyBatisDao.insert(ShopDao.class, data);
                //保存商家资金表
                saveShopMoney(data);

               return "0";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "1";
    }

    private void saveShopMoney(Shop data) {
        ShopMoney money = new ShopMoney();
        money.setId(UUIDFactory.random());
        money.setShopId(data.getId());
        money.setShopName(data.getName());
        money.setAvailAmount("0");
        money.setFrozenAmount("0");
        money.setTotalAmount("0");
        this.baseMyBatisDao.insert(ShopMoneyDao.class, money);
    }

    @Override
    public List<Shop> listByIds(String[] strings) {
        return shopDao.listByIds(strings);
    }

    @Override
    public List<Shop> listByName(String name) {
        return shopDao.listByName(name);
    }

    @Override
    public Integer countShop(Map params) {
        return this.shopDao.countShop(params);
    }



   /**********************************  接口  **********************************

    /**
     *
     * @param account
     * @param password
     * @return
     */
    @Override
    public RetResult restLogin(String account, String password) {
        RetResult result = null;
        try {
            List<Shop> list = shopDao.listByAccount(account);
            if (StringUtil.isNotEmpty(list)) {
                Shop shop = list.get(0);
                if(shop.getPassword().equals(MD5Util.getMD5ofStr(password))){
                    if(!"NORMAL".equals(shop.getStatus())){
                        result = new RetResult(RetResult.RetCode.Shop_Status_Valid);
                    }else{
                        result = new RetResult(RetResult.RetCode.OK);
                        RetData data = new RetData(shop);
                        result.setData(data);
                    }
                }else{
                    result = new RetResult(RetResult.RetCode.Shop_Pwd_Error);
                }
            }else{
                result = new RetResult(RetResult.RetCode.Shop_Not_Exist);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new RetResult(RetResult.RetCode.Execute_Error);
        }
        return result;
    }

    @Override
    public RetResult updateInfo(String id, String linkName, String linkMobile, String linkAddress) {
        RetResult result = null;
        try {
            Shop shop = baseMyBatisDao.findById(ShopDao.class, id);
            if (shop!=null) {
                shop.setLinkName(linkName);
                shop.setLinkMobile(linkMobile);
                shop.setLinkAddress(linkAddress);
                this.baseMyBatisDao.update(ShopDao.class, shop);
                result = new RetResult(RetResult.RetCode.OK);
            }else{
                result = new RetResult(RetResult.RetCode.Shop_Not_Exist);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new RetResult(RetResult.RetCode.Execute_Error);
        }
        return result;
    }

    @Override
    public RetResult updatePwd(String id, String oldPwd, String newPwd) {
        RetResult result = null;
        try {
            Shop shop = baseMyBatisDao.findById(ShopDao.class,id);
            if (shop!=null) {
                if(shop.getPassword().equals(MD5Util.getMD5ofStr(oldPwd))){
                    shop.setPassword(MD5Util.getMD5ofStr(newPwd));
                    this.baseMyBatisDao.update(ShopDao.class, shop);
                    result = new RetResult(RetResult.RetCode.OK);
                }else{
                    result = new RetResult(RetResult.RetCode.Shop_Pwd_Error);
                }
            }else{
                result = new RetResult(RetResult.RetCode.Shop_Not_Exist);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new RetResult(RetResult.RetCode.Execute_Error);
        }
        return result;
    }

    @Override
    public RetResult list(String name,String shopTypeKey,String sortType,String type,String code, int pageNo, int pageSize) {
        RetResult result = null;
        try{
            if(pageNo<1)
                pageNo = 1;
            List<RestShop> list = this.shopDao.list(name,shopTypeKey,sortType,type,code,(pageNo-1)*pageSize,pageSize);
            Integer count = this.shopDao.count(name,shopTypeKey,sortType,type,code);
            initSaleCount(list);
            ShopType shopType = null;
            if(!StringUtil.isNullOrEmpty(shopTypeKey)) {
                List<ShopType> types = shopTypeDao.listByKey(shopTypeKey);
                if(types != null && types.size() > 0) {
                    shopType = types.get(0);
                }
            }
            if(!StringUtil.isNullOrEmpty(type) && shopType == null) {
                shopType = this.baseMyBatisDao.findById(ShopTypeDao.class, type);
            }
            if(shopType != null && "1".equals(shopType.getIsProduct())) {
                initProducts(list);
            }
            RetData data = new RetData(pageNo,pageSize,list,count);
            result = new RetResult(RetResult.RetCode.OK);
            result.setData(data);
        }catch (Exception e){
            e.printStackTrace();
            result = new RetResult(RetResult.RetCode.Execute_Error);
        }
        return result;
    }

    @Override
    public RetResult juliList(String lat,String lng,String name, String shopTypeKey, String type, int pageNo, int pageSize) {
        RetResult result = null;
        try{
            if(pageNo<1)
                pageNo = 1;
            List<RestShop> list = this.shopDao.juliList(lat, lng, name, shopTypeKey, type, (pageNo - 1) * pageSize, pageSize);
            Integer count = this.shopDao.count(name,shopTypeKey,null,type,null);
            initSaleCount(list);
            ShopType shopType = null;
            if(!StringUtil.isNullOrEmpty(shopTypeKey)) {
                List<ShopType> types = shopTypeDao.listByKey(shopTypeKey);
                if(types != null && types.size() > 0) {
                    shopType = types.get(0);
                }
            }
            if(!StringUtil.isNullOrEmpty(type) && shopType == null) {
                shopType = this.baseMyBatisDao.findById(ShopTypeDao.class, type);
            }
            if(shopType != null && "1".equals(shopType.getIsProduct())) {
                initProducts(list);
            }
            RetData data = new RetData(pageNo,pageSize,list,count);
            result = new RetResult(RetResult.RetCode.OK);
            result.setData(data);
        }catch (Exception e){
            e.printStackTrace();
            result = new RetResult(RetResult.RetCode.Execute_Error);
        }
        return result;
    }

    private void initProducts(List<RestShop> list){
        if(list != null && list.size() > 0) {
            List<String> shopIds = new ArrayList<>();
            for(RestShop shop : list){
                shopIds.add(shop.getId());
            }
            List<Product> products = productDao.searchExtendProductByShopIds(shopIds.toArray(new String[0]));
            if(products != null && products.size() > 0) {
                Map<String,List<Product>> map = new HashMap<>();
                List<Product> temp = null;
                for(Product product : products) {
                    if(map.get(product.getShopId()) != null) {
                        temp = map.get(product.getShopId());
                    }else{
                        temp = new ArrayList<>();
                    }
                    temp.add(product);
                    map.put(product.getShopId(), temp);
                }
                for(RestShop shop : list) {
                    shop.setProducts(map.get(shop.getId()));
                }
            }
        }
    }
    private void initSaleCount(List<RestShop> list){
        if(list != null && list.size() > 0) {
            List<String> shopIds = new ArrayList<>();
            for(RestShop shop : list){
                shopIds.add(shop.getId());
            }
            List<Product> products = productDao.searchSaleCountGroupByShopId(shopIds.toArray(new String[0]));
            if(products != null && products.size() > 0) {
                Map<String,Integer> map = new HashMap<>();
                for(Product product : products){
                    map.put(product.getShopId(), product.getSaleCount() + product.getVirtualCount());
                }
                for(RestShop shop : list) {
                    shop.setSaleCount(map.get(shop.getId()) == null ? 0 : map.get(shop.getId()));
                }
            }
        }
    }
}
