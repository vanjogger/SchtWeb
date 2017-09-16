package com.scht.common;

import com.scht.front.bean.RetResult;

/**
 * Created by wxh on 2016/12/11.
 */
public class BaseFrontController {

    /**
     * 接口中公共的验证参数方法
     *
     * @param au
     * @return
     */
    public RetResult validateParam(String au) {

        RetResult result = null;
        String[] params = au.split(":");
        if (params != null && params.length == 2) {
            String userId = params[0];
            String identify = params[1];

        } else if (params != null && params.length == 1) {
           // result = ValidateUtil.validateIndentify(params[0]);
        } else {
           // result = new RetResult(RetCode.BadRequest);
        }
        return result;
    }

}
