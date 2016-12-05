package com.scht.admin.task;

import com.scht.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * Created by Administrator on 2016/12/5.
 */
@Component
public class OrderTask {

    @Autowired
    OrderService orderService;

    public String execute(){
        orderService.updateTask();
        return null;
    }
}
