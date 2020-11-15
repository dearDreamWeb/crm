package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.CustomerRecord;
import com.example.entity.ResultVo;
import com.example.model.mapper.CustomerRecordMapper;
import com.example.service.CustomerRecordService;
import com.example.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/11/15
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerRecordServiceImpl implements CustomerRecordService {
    @Autowired
    private CustomerRecordMapper recordMapper;

    @Override
    public ResultVo addCustomerRecord(CustomerRecord customerRecord) {
        int addCustomerRecord = recordMapper.addCustomerRecord(customerRecord);
        if (addCustomerRecord != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addCustomerRecord);
    }

    @Override
    public ResultVo listCustomerRecord(CustomerRecord customerRecord) {
        List<CustomerRecord> customerRecords = recordMapper.listCustomerRecord(customerRecord);
        return ResultUtils.response(customerRecords);
    }
}
