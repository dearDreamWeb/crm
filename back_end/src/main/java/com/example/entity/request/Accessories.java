package com.example.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: liuleimin
 * @date: 2020/11/6
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accessories {
    private Integer accessId;
    private Integer empId;
    private Integer repairId;
    private double accessSjcharge;
    private Integer accessShul;
    private Integer pageNum;
    private Integer pageSize;
}
