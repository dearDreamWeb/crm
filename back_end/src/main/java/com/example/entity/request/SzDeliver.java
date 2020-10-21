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
    private Date delDelivertime;//发货时间
    private Integer delState;//状态（0未发/1已发）
    private Date delCreatetime;//创建时间
    private Integer delDelete;//1是0否删除

    private Integer ordId;//订单编号 (外键 关联订单)
    private SzOrder szorder;

    private String startDate;
    private String endDate;
    private Integer pageNum;
    private Integer pageSize;
}
