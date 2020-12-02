package com.example.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/11/20
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferResp {
    private Integer offerId;
    private String offerTheme;
    private String offerNumbers;
    private Integer offerStatus;
    private BigDecimal offerPrice;
    private String examinePerson;
    private Date examineTime;
    private String remark;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
    private Integer contactsId;
    private Integer saleId;
    private Integer empId;
    private Integer isTurn;

    private ContactsResp contactsResp;
    private SaleResp saleResp;
    private EmpResp empResp;
    private List<OfferDetailResp> offerDetailResp;
}
