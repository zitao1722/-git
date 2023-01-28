package com.zitao.seata.service.impl;

import com.zitao.seata.entity.Product;
import com.zitao.seata.mappers.ProductMapper;
import com.zitao.seata.service.ProductService;
import io.seata.spring.annotation.GlobalLock;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * (ProductTbl)表服务实现类
 *
 * @author zitao
 * @since 2022-11-16 10:19:13
 */
@Service("productService")
//@GlobalTransactional
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param productId 主键
     * @return 实例对象
     */
    @Override
    public Product queryById(Integer productId) {
        return this.productMapper.queryById(productId);
    }


    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public int insert(Product product) {
        int result = productMapper.insert(product);
        return result;
    }

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    @Trace
    @Tag(key = "result", value = "returnedObj")
    public int update(Product product) {
        int result = productMapper.updateCount(product);
        return result;
    }

    @Override
    @Transactional
    public int incr(Product product) {
        int result = productMapper.incrCount(product);
        return result;
    }

    /**
     * 通过主键删除数据
     *
     * @param productId 主键
     * @return 是否成功
     */
    @Override
    @Transactional
    public boolean deleteById(Integer productId) {
        return this.productMapper.deleteById(productId) > 0;
    }
}
