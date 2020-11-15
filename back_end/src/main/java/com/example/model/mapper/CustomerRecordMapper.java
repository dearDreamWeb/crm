package com.example.model.mapper;

import com.example.entity.CustomerRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/11/15
 * @description:
 */
@Repository
public interface CustomerRecordMapper {
    int addCustomerRecord(CustomerRecord customerRecord);
    List<CustomerRecord> listCustomerRecord(CustomerRecord customerRecord);
}
