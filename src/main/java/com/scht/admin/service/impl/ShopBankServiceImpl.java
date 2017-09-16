package com.scht.admin.service.impl;

import com.scht.admin.dao.BaseMyBatisDao;
import com.scht.admin.dao.MemberDao;
import com.scht.admin.dao.ShopBankDao;
import com.scht.admin.dao.ShopDao;
import com.scht.admin.entity.Shop;
import com.scht.admin.entity.ShopBank;
import com.scht.admin.service.ShopBankService;
import com.scht.front.bean.RestProduct;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import com.scht.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wxh on 2016/12/2.
 */
@Service
@Transactional
public class ShopBankServiceImpl implements ShopBankService {

    @Autowired
    ShopBankDao shopBankDao;
    @Autowired
    BaseMyBatisDao baseMyBatisDao;



    @Override
    public ShopBank findById(String id) {
        return shopBankDao.findById(id);
    }

    @Override
    public void update(ShopBank bank){
            this.shopBankDao.update(bank);
    }


    /**
     *  **************  接口  ***************
     */
    /**
     *
     * @param shopId
     * @param pageNo
     * @param pageSize
     * @return
     */

    @Override
    public RetResult list(String shopId, int pageNo, int pageSize) {
        RetResult result = null;
        try{
            if(pageNo<1)
                pageNo = 1;
            List<RestProduct> list = this.shopBankDao.list(shopId,(pageNo-1)*pageSize,pageSize);
            Integer count = this.shopBankDao.count(shopId);

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
    public RetResult save(String shopId, String yhmc, String khh, String ckr, String kh) {
        RetResult result = null;
        try{
            Shop shop = this.baseMyBatisDao.findById(ShopDao.class,shopId);
            if(shop!=null) {
                ShopBank bank = new ShopBank();
                bank.setId(UUIDFactory.random());
                bank.setShopId(shopId);
                bank.setYhmc(yhmc);
                bank.setKhh(khh);
                bank.setCkr(ckr);
                bank.setKh(kh);
                this.baseMyBatisDao.insert(ShopBankDao.class,bank);
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
    public RetResult edit(String id, String yhmc, String khh, String ckr, String kh) {
        RetResult result = null;
        try{
            ShopBank bank = this.baseMyBatisDao.findById(ShopBankDao.class,id);
            if(bank!=null) {
                bank.setYhmc(yhmc);
                bank.setKhh(khh);
                bank.setCkr(ckr);
                bank.setKh(kh);
                this.baseMyBatisDao.update(ShopBankDao.class, bank);
                result = new RetResult(RetResult.RetCode.OK);
            }else{
                result = new RetResult(RetResult.RetCode.Shop_Bank_Not_Exist);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new RetResult(RetResult.RetCode.Execute_Error);
        }
        return result;
    }

    @Override
    public RetResult delete(String id) {
        RetResult result = null;
        try{
            ShopBank bank = this.baseMyBatisDao.findById(ShopBankDao.class,id);
            if(bank!=null) {
                this.baseMyBatisDao.delete(ShopBankDao.class,new String[]{id});
                result = new RetResult(RetResult.RetCode.OK);
            }else{
                result = new RetResult(RetResult.RetCode.Shop_Bank_Not_Exist);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new RetResult(RetResult.RetCode.Execute_Error);
        }
        return result;
    }
}
