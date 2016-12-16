package com.scht.admin.service.impl;

import com.scht.admin.dao.*;
import com.scht.admin.entity.Member;
import com.scht.admin.entity.Order;
import com.scht.admin.entity.Product;
import com.scht.admin.entity.ProductComment;
import com.scht.admin.service.ProductCommentService;
import com.scht.front.bean.RetData;
import com.scht.front.bean.RetResult;
import com.scht.util.StringUtil;
import com.scht.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by vanjoger on 2016/12/10.
 */
@Service
public class ProductCommentServiceImpl implements ProductCommentService {

    @Autowired
    ProductCommentDao productCommentDao;
    @Autowired
    BaseMyBatisDao baseMyBatisDao;

    @Override
    public RetResult list(String memberId,String productId, int pageNo, int pageSize) {
        RetResult result = null;
        try{
            if(pageNo<1)
                pageNo = 1;
            List<ProductComment> list = this.productCommentDao.list(memberId,productId,(pageNo-1)*pageSize,pageSize);
            Integer total = this.productCommentDao.count(memberId,productId);

            RetData data = new RetData(pageNo,pageSize,list,total);
            result = new RetResult(RetResult.RetCode.OK);
            result.setData(data);
        }catch (Exception e){
            e.printStackTrace();
            result = new RetResult(RetResult.RetCode.Execute_Error);
        }
        return result;
    }

    @Override
    public RetResult save(String orderId, String productId, String grade, String images, String content, String memberId) {
        RetResult result = null;
        try{
            Order order = this.baseMyBatisDao.findById(OrderDao.class, orderId);
            if(order==null) {
                result = new RetResult(RetResult.RetCode.Not_Allowed_Comment_Order);
                return result;
            }
            Product product = this.baseMyBatisDao.findById(ProductDao.class, productId);
            if(product==null){
                result = new RetResult(RetResult.RetCode.Not_Allowed_Comment_Product);
                return result;
            }
            Member member = this.baseMyBatisDao.findById(MemberDao.class, memberId);
            if(member==null){
                result = new RetResult(RetResult.RetCode.Not_Allowed_Comment_Member);
                return  result;
            }

            //检测会员是否已经对商品评价过
            List<ProductComment> list = this.productCommentDao.queryByMemberIdandProductId(memberId, productId);
            if(StringUtil.isNotEmpty(list)) {
                result = new RetResult(RetResult.RetCode.Already_Comment);
                return result;
            }

            ProductComment comment = new ProductComment();
            comment.setId(UUIDFactory.random());
            comment.setProductId(productId);
            comment.setProductName(product.getTitle());
            comment.setStatus("0");//未评论
            comment.setShopId(order.getShopId());
            comment.setOrderId(orderId);
            comment.setContent(content);
            comment.setImages(images);
            comment.setGrade(grade);
            comment.setMemberId(memberId);
            comment.setCreateTime(new Date().getTime());
            this.baseMyBatisDao.insert(ProductCommentDao.class, comment);

            result = new RetResult(RetResult.RetCode.OK);
            order.setMemberAssess("1");
            this.baseMyBatisDao.update(OrderDao.class, order);
        }catch (Exception e){
            e.printStackTrace();
            result = new RetResult(RetResult.RetCode.Execute_Error);
        }
        return result;
    }

    @Override
    public RetResult delete(String id) {
        RetResult result = null;
        try{
            if(StringUtil.isNotNull(id)) {
                ProductComment comment = this.baseMyBatisDao.findById(ProductCommentDao.class,id);
                if(comment!=null) {
                    this.baseMyBatisDao.delete(ProductCommentDao.class, new String[]{id});
                    result = new RetResult(RetResult.RetCode.OK);
                }else{
                    result = new RetResult(RetResult.RetCode.Object_Not_Exist);
                }
            }else{
                result = new RetResult(RetResult.RetCode.Object_Not_Exist);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new RetResult(RetResult.RetCode.Execute_Error);
        }
        return result;
    }
}
