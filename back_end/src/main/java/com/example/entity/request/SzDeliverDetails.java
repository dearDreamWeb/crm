package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<SzDeliverMingxi> szDeliverMingxi;//一个发货详情对应多个明细

}
