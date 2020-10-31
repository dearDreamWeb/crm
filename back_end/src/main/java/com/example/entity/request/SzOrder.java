package com.example.entity.request;

import com.example.entity.response.CustomerResp;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/*
YQQ
1002
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SzOrder {
    private Integer ordId;//订单编号
    private String ordTheme;//主题
    private Integer ordState;//订单状态
    private String ordHead;//负责人
    private BigDecimal ordTotalmoney;//总金额
    private Date ordStarttime;// 开始时间(签单时间）
    private Date ordDealtime;//成交时间
    private Date ordFahuotime;//发货时间
    private BigDecimal ordHuikuanmoney;//回款金额
    private String ordConsignee;//收货人
    private String ordProvince;//省份
    private String ordCity;//市
    private String ordCountry;//区/县
    private String ordDetail;//区/县
    private String ordPhone;//收货手机号
    private Date ordCreatetime;//订单创建时间（不是签单时间)
    private Integer ordDelete;//是否删除

    private Integer cusId;//客户编号
    private Integer offerId;//报价编号

    private CustomerResp customerResp;//客户
    List<SzOrderDetails> SzOrderDetails;

    private String startDate;
    private String endDate;
    private Integer pageNum;
    private Integer pageSize;
    private List<ProductReq> addproplus;
}
