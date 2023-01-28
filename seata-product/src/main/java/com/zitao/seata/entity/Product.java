package com.zitao.seata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (ProductTbl)实体类
 *
 * @author zitao
 * @since 2022-11-16 10:19:10
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product implements Serializable {
    private static final long serialVersionUID = 285392058034266318L;

    private Integer id;

    private Integer productId;

    private Integer count;


}

