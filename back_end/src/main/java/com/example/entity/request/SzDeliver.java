package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
    private Integer delState;//状态（0未发/1已发）
    private Date delCreatetime;//创建时间
    private Integer delDelete;//1是0否删除
    /*
    private String ordProvince;//省份
    private String ordCity;//市
    private String ordCountry;//区/县
    private String ordDetail;//详情地址*/

    private Date delExpecttime;//预计发货时间
    private Date delActualtime;//实际发货时间


    private Integer ordId;//订单编号 (外键 关联订单)
    private SzOrder szOrder;//11
    private List<SzDeliverDetails> szDeliverDetails;//1n
    private List<ProductReq> productReq;

    private String startDate;
    private String endDate;
    private Integer pageNum;
    private Integer pageSize;
}
