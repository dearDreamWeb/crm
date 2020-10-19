package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: yangqianqian
 * @date: 2020/10/19
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SzDeliver {
    private Integer delId;//发货单编号
    private String delWuliuid;//物流编号
    private String delCompany;//物流公司
    private String delPeople;//发货人
    private Date delExpecttime;//预计到货时间
    private Date delActualtime;//实际到货时间
    private Date delState;//状态（0未发/1已发）

    private Integer ordId;//订单编号 (外键 关联订单)
    private Integer pageNum;
    private Integer pageSize;
}
