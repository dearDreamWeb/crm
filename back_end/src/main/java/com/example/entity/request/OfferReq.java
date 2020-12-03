package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: pengjia
 * @date: 2020/11/20
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferReq {
    private Integer offerId;
    private String offerTheme;
    private String offerNumbers;
    private Integer offerStatus;
    private BigDecimal offerPrice;
    private String examinePerson;
    private Date examineTime;
    private String remark;
    private Integer version;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
    private Integer contactsId;
    private Integer saleId;
    private Integer empId;
    private Integer isTurn;

    private Integer pageNum;
    private Integer pageSize;
    private String startDate;
    private String endDate;
}
