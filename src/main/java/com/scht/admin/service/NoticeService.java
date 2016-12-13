package com.scht.admin.service;

import com.scht.front.bean.RetResult;

/**
 * Created by vanjoger on 2016/12/13.
 */
public interface NoticeService {
    RetResult list(String no);

    RetResult detail(String id);
}
