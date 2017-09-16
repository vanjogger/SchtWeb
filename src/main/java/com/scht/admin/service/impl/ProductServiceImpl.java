package com.scht.admin.service.impl;

import com.scht.admin.dao.BaseMyBatisDao;
import com.scht.admin.dao.ProductDao;
import com.scht.admin.entity.Product;
import com.scht.admin.service.ProductService;
import com.scht.front.bean.RestProduct;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wxh on 2016/12/11.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Autowired
    BaseMyBatisDao baseMyBatisDao;

    @Override
    public RetResult list(String id, String productName, int pageNo, int pageSize) {
        RetResult result = null;
        try{
            if(pageNo<1)
                pageNo = 1;
            List<RestProduct> list = this.productDao.list(id,productName,(pageNo-1)*pageSize,pageSize);
            Integer count = this.productDao.count(id, productName);

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
    public List regionList(Map<String, Object> map) {
       return productDao.regionList(map);
    }
}
