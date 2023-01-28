package com.zitao.seata.mappers;

import com.zitao.seata.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    int insert(Order record);

    int insertSelective(Order record);

    int deleteOrder(Order order);

    Order selectById(Integer id);
}