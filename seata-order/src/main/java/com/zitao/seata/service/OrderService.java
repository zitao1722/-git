package com.zitao.seata.service;

import com.zitao.seata.entity.Order;

public interface OrderService {
    int addOrder(Order order);

    int addOrderByParam(Order order);

    int cancelOrder(Integer orderId);
}
