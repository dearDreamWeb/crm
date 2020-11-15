package com.example.service;

import com.example.entity.CustomerRecord;
import com.example.entity.ResultVo;

/**
 * @author: pengjia
 * @date: 2020/11/15
 * @description:
 */
public interface CustomerRecordService {
    ResultVo addCustomerRecord(CustomerRecord customerRecord);
    ResultVo listCustomerRecord(CustomerRecord customerRecord);
}
