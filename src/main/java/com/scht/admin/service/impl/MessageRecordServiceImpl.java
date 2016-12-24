package com.scht.admin.service.impl;

import com.scht.admin.dao.BaseMyBatisDao;
import com.scht.admin.dao.MessageRecordDao;
import com.scht.admin.entity.MessageRecord;
import com.scht.admin.service.MessageRecordService;
import com.scht.front.bean.RetResult;
import com.scht.front.util.SmsUtil;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by vanjoger on 2016/12/13.
 */
@Service
public class MessageRecordServiceImpl implements MessageRecordService {

    private Logger logger = LoggerFactory.getLogger(MessageRecordServiceImpl.class);

    @Autowired
    MessageRecordDao messageRecordDao;
    @Autowired
    BaseMyBatisDao baseMyBatisDao;

    @Override
    public RetResult sendSms(String mobile, String type) {
        RetResult result = null;
        try {
            // 限制30分钟内向某个手机号码最多发送5条短信
            List<MessageRecord> smsRecordList = this.messageRecordDao.listByMobile(mobile, type);
            if (smsRecordList != null && smsRecordList.size() >= 5) {
                MessageRecord lastRecord = smsRecordList.get(4);// 已通过时间排序 取最早的一条记录
                long mills = new Date().getTime() - lastRecord.getCreateTime();
                if (mills <= (30 * 60 * 1000)) {
                    return new RetResult(RetResult.RetCode.Sms_Frequent);// 30分钟限制只能发送5条短信
                }
            }
            // 6位随机码
            String randomCode = StringUtil.getRandomNum(6);

            String content = "用户您好！您的手机验证码是" + randomCode + "，请您及时输入。若5分钟内未输入，需要重新获取验证码。";
            // 发送短信 预留
            Map map = SmsUtil.sendSms(mobile, content);
            System.out.println("短信接口返回：---------  "+map);
            if (map != null && (map.get("code").equals("2"))) {
                // 保存记录
                MessageRecord record = new MessageRecord();
                record.setId(UUIDFactory.random());
                record.setTelephone(mobile);
                record.setMessage(randomCode);
                record.setCode(randomCode);
                record.setCreateTime(new Date().getTime());
                record.setType(type);
                record.setStatus("0");// 发送成功
                this.baseMyBatisDao.insert(MessageRecordDao.class,record);
                result = new RetResult(RetResult.RetCode.OK);
            } else {
                result = new RetResult(RetResult.RetCode.Sms_Fail);
            }
            // }
        } catch (Exception e) {
            e.printStackTrace();
            result = new RetResult(RetResult.RetCode.Execute_Error);
        }
        return result;

    }

    @Override
    public RetResult validateCode(String mobile, String code) {
        RetResult result = null;
        try {
            List<MessageRecord> recordlist = this.messageRecordDao.listByMobileAndCode(mobile, code);
            if (recordlist != null && recordlist.size() > 0) {
                MessageRecord record = recordlist.get(0);// 比较时间有效性
                long mills = new Date().getTime() - record.getCreateTime();
                if (mills > 5 * 60 * 1000) {
                    result = new RetResult(RetResult.RetCode.Sms_InValid);
                } else {
                    result = new RetResult(RetResult.RetCode.OK);
                }
            } else {
                result = new RetResult(RetResult.RetCode.ValidateCode_Error);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new RetResult(RetResult.RetCode.Execute_Error);
        }
        return result;
    }
}
