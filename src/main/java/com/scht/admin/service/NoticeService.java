package com.scht.admin.service;

import com.scht.front.bean.RetResult;

/**
 * Created by wxh on 2016/12/13.
 */
public interface NoticeService {
    RetResult list(String no);

    RetResult detail(String id);
}
