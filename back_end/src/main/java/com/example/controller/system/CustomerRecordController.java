package com.example.controller.system;

import com.example.entity.CustomerRecord;
import com.example.entity.ResultVo;
import com.example.service.CustomerRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: pengjia
 * @date: 2020/11/26
 * @description:
 */
@RestController
@RequestMapping("/customer_record")
public class CustomerRecordController {
    @Autowired
    private CustomerRecordService recordService;

    @GetMapping("/list")
    public ResultVo listCustomerRecord(CustomerRecord customerRecord) {
        return recordService.listCustomerRecord(customerRecord);
    }
}
