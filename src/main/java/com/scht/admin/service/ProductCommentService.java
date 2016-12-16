package com.scht.admin.service;

import com.scht.front.bean.RetResult;

/**
 * Created by vanjoger on 2016/12/10.
 */
public interface ProductCommentService {
    RetResult list(String productId, int pageNo, int pageSize);

    RetResult save(String orderId, String productId, String grade, String images, String content, String memberId);

    RetResult delete(String id);
}
