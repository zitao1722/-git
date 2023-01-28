package com.zitao.seata.service;

import com.zitao.seata.entity.Product;

/**
 * (ProductTbl)表服务接口
 *
 * @author zitao
 * @since 2022-11-16 10:19:13
 */
public interface ProductService {

    /**
     * 通过ID查询单条数据
     *
     * @param productId 主键
     * @return 实例对象
     */
    Product queryById(Integer productId);


    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    int insert(Product product);

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    int update(Product product);

    int incr(Product product);

    /**
     * 通过主键删除数据
     *
     * @param productId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer productId);

}
