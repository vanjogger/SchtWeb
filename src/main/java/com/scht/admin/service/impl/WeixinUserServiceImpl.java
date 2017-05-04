package com.scht.admin.service.impl;

import com.scht.admin.bean.WeixinUser;
import com.scht.admin.dao.BaseMyBatisDao;
import com.scht.admin.dao.WeixinPaySetDao;
import com.scht.admin.dao.WeixinUserDao;
import com.scht.admin.entity.WeixinPaySet;
import com.scht.admin.service.WeixinUserService;
import com.scht.util.UUIDFactory;
import com.scht.util.WeixinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wxh on 2017/5/3.
 */
@Service
public class WeixinUserServiceImpl implements WeixinUserService {

    @Autowired
    BaseMyBatisDao baseMyBatisDao;
    @Override
    public void weixinUserTask() {
        WeixinPaySet set = this.baseMyBatisDao.findById(WeixinPaySetDao.class,"");
        List<WeixinUser> list = this.baseMyBatisDao.findAll(WeixinUserDao.class);
        Map<String,String> map = new HashMap<>();
        if(list != null && list.size() > 0) {
            for(WeixinUser user : list) {
                map.put(user.getOpenId(), user.getOpenId());
            }
        }
        try {
            List<String> openIdList = WeixinUtil.getUserList(set);
            List<WeixinUser> saveList = new ArrayList<>();
            if(openIdList != null && openIdList.size() > 0) {
                for(String id : openIdList) {
                    if(map.get(id) == null) {//新用户
                        WeixinUser user = WeixinUtil.getUser(set,id);
                        if(user != null) {
                            user.setId(UUIDFactory.random());
                            saveList.add(user);
                        }
                    }
                }
                if(saveList.size() > 0) {
                    int len = saveList.size();
                    int end =10;
                    int size = len%end > 0 ? len/end+1 : len/end;
                    for(int i=0; i<size;i++){
                        if(i != size-1) {
                            this.baseMyBatisDao.saveBatch(WeixinUserDao.class, saveList.subList(i * end, (i + 1) * end));
                        }else{
                            this.baseMyBatisDao.saveBatch(WeixinUserDao.class, saveList.subList(i * end, len));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
