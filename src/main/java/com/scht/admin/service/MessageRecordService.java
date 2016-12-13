package com.scht.admin.service;

import com.scht.front.bean.RetResult;

/**
 * Created by vanjoger on 2016/12/13.
 */
public interface MessageRecordService {
    RetResult sendSms(String mobile, String type);

    RetResult validateCode(String mobile, String codee);

}
