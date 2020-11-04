package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: pengjia
 * @date: 2020/11/2
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleReq {
    private Integer saleId;
    private String saleName;
    private String saleStatus;
    private String saleType;
    private String saleSource;
    private String discoveryTime;
    private Integer version;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
    private Integer cusId;
    private Integer contactsId;
    private Integer empId;
    private Integer pageNum;
    private Integer pageSize;
    private String startDate;
    private String endDate;


    public void setSaleId(Integer s){
        System.out.println("成功赋值。。。"+s);
        this.saleId = s;
    }

    public Integer getSaleId(){
        System.out.println("正在是JFK"+this.saleId);
        return this.saleId;
    }
}
