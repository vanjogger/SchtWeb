package com.scht.admin.dao;

import com.scht.admin.entity.MessageRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/12/5.
 */
@Repository
public interface MessageRecordDao {

    //查询Time之后，验证码为code的短信是否存在
    MessageRecord findByCode(@Param("code")String code, @Param("time")long time);

}