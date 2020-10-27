package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: pengjia
 * @date: 2020/10/26
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReq {
    private Integer productId;
    private String productName;
    private String productImage;
    private String productModel;
    private BigDecimal productPrice;
    private String productBrand;
    private Integer productStock;
    private String productSize;
    private BigDecimal productCost;
    private String productBarCode;
    private Integer productStatus;
    private String remark;
    private Integer version;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
    private Integer pageNum;
    private Integer pageSize;
    private String startDate;
    private String endDate;
    private Integer productCount;
}
