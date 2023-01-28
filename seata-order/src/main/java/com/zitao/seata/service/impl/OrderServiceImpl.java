package com.zitao.seata.service.impl;

import com.zitao.seata.entity.Order;
import com.zitao.seata.feign.SeataProductFeignService;
import com.zitao.seata.mappers.OrderMapper;
import com.zitao.seata.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SeataProductFeignService seataProductFeignService;

    @Override
    public int addOrder(Order order) {
        return orderMapper.insert(order);
    }

    /**
     * 开启事务操作
     *
     * @param order
     * @return
     */
//    @Transactional
    // 注释掉原来的事务注解，使用seata提供的 @GlobalTransactional 注解
//    @GlobalTransactional
    @Override
    @GlobalTransactional
    @Trace
    @Tag(key = "result", value = "returnedObj")
    public int addOrderByParam(Order order) {
//        lock.lock();  // 程序报错，无法获取全局锁，尝试在这里加锁（无用）
        int result = orderMapper.insertSelective(order);

        // 通过feign进行远程调用其他服务，上面和下面都涉及了数据库操作，这也就有了分布式事务的问题
        String productResult = seataProductFeignService.get(order.getId(), order.getProductId());
        if ("更新库存失败，请稍后重试".equals(productResult)) {
            result = -1;
        }
        // 模拟分布式事务，当本服务出现异常了进行回滚，它所调用的其他服务会不会一起回滚
        // 因为后面集成skywalking，暂时注释掉
//        int a = 1 / 0;
//        lock.unlock();
        return result;
    }

    //    @GlobalTransactional
    @Override
    @GlobalTransactional
    public int cancelOrder(Integer orderId) {
        Order order = orderMapper.selectById(orderId);
        int result = orderMapper.deleteOrder(order);
        String productResult = seataProductFeignService.add(order.getProductId());
        if ("更新库存失败，请稍后重试".equals(productResult)) {
            result = -1;
        }
        return result;
    }
}
