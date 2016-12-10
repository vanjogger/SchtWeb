package com.scht.admin.service.impl;

import com.scht.admin.dao.ProductCommentDao;
import com.scht.admin.service.ProductCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by vanjoger on 2016/12/10.
 */
@Service
public class ProductCommentServiceImpl implements ProductCommentService {

    @Autowired
    ProductCommentDao productCommentDao;



}
