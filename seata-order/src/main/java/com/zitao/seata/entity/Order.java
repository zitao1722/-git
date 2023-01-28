package com.zitao.seata.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * order_tbl
 *
 * @author
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer productId;

    private Integer totalAmount;

    private Integer status;

}