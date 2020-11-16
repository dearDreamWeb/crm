package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: pengjia
 * @date: 2020/11/16
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ration {
    private Integer rationId;
    private String rationIcon;
    private String rationName;
    private Date rationTime;
    private Date expectSigningTime;
    private BigDecimal expectSigningPrice;
    private Integer expectSigningNumber;
    private Integer cusId;
    private Integer rationEmpId;
}
