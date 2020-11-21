package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yangqianqian
 * @date: 2020/11/21
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SzDeliverDetails {
    private Integer ddetId;//发货详情编号
    private Integer ddetNum;//发货数量

    private Integer delId;//发货单编号
    private Integer productId;//产品编号

    private SzDeliver szDeliver;//发货单

}
