package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.CusConReq;
import com.example.entity.ResultVo;
import com.example.entity.request.ContactsReq;
import com.example.entity.request.CustomerReq;
import com.example.entity.response.CustomerResp;
import com.example.model.mapper.ContactsMapper;
import com.example.model.mapper.CustomerMapper;
import com.example.service.CustomerService;
import com.example.util.CheckUtils;
import com.example.util.DateUtils;
import com.example.util.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/21
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ContactsMapper contactsMapper;

    @Override
    public ResultVo addCustomer(CustomerReq customerReq) {
        CheckUtils.validate(customerReq);
        int addCustomer = customerMapper.addCustomer(customerReq);
        if (addCustomer != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addCustomer);
    }

    @Override
    public ResultVo delCustomer(CustomerReq customerReq) {
        Integer cusId = customerReq.getCusId();
        CustomerResp customer = customerMapper.getCustomer(cusId);
        if (customer == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        int delCustomer = customerMapper.delCustomer(cusId);
        if (delCustomer != 1) {
            throw new SysException(ResultEnum.DATA_DEL_FAIL.getCode(),
                    ResultEnum.DATA_DEL_FAIL.getMessage());
        }
        return ResultUtils.response(delCustomer);
    }

    @Override
    public ResultVo editCustomer(CustomerReq customerReq) {
        Integer cusId = customerReq.getCusId();
        CustomerResp customer = customerMapper.getCustomer(cusId);
        if (customer == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        customerReq.setVersion(customer.getVersion());
        customerReq.setUpdateTime(DateUtils.getDate());
        int editCustomer = customerMapper.editCustomer(customerReq);
        if (editCustomer != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(editCustomer);
    }

    @Override
    public ResultVo getCustomer(Integer cusId) {
        CustomerResp customer = customerMapper.getCustomer(cusId);
        return ResultUtils.response(customer);
    }

    @Override
    public ResultVo listCustomer(CustomerReq customerReq) {
        Integer pageNum = customerReq.getPageNum();
        Integer pageSize = customerReq.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<CustomerResp> customerResps = customerMapper.listCustomer(customerReq);
        PageInfo<CustomerResp> list = new PageInfo<>(customerResps);
        return ResultUtils.response(list);
    }

    @Override
    public ResultVo addCusAndCon(CusConReq cusConReq) {
        CustomerReq customerReq = new CustomerReq();
        ContactsReq contactsReq = new ContactsReq();

        customerReq.setCusName(cusConReq.getCusName());
        customerReq.setAbbreviation(cusConReq.getAbbreviation());
        customerReq.setCusDictSource(cusConReq.getCusDictSource());
        customerReq.setCusRemark(cusConReq.getCusRemark());
        customerReq.setEmpId(cusConReq.getEmpId());

        contactsReq.setContactsName(cusConReq.getContactsName());
        contactsReq.setContactsPhone(cusConReq.getContactsPhone());
        contactsReq.setQq(cusConReq.getQq());
        contactsReq.setWechat(cusConReq.getWechat());
        int addCustomer = customerMapper.addCustomerAndContacts(customerReq);
        if (addCustomer != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        contactsReq.setCusId(customerReq.getCusId());
        int addContacts = contactsMapper.addContactsAndCustomer(contactsReq);
        if (addContacts != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        return ResultUtils.response("新增成功");
    }

}
