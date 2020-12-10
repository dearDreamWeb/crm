package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.CusConReq;
import com.example.entity.CustomerRecord;
import com.example.entity.ResultVo;
import com.example.entity.request.ClueReq;
import com.example.entity.request.ContactsReq;
import com.example.entity.request.CustomerReq;
import com.example.entity.response.*;
import com.example.model.mapper.*;
import com.example.service.CustomerService;
import com.example.util.CheckUtils;
import com.example.util.DateUtils;
import com.example.util.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private CustomerRecordMapper recordMapper;

    @Autowired
    private ClueFollowLogMapper clueFollowLogMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ContactsMapper contactsMapper;

    @Autowired
    private ClueMapper clueMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DictMapper dictMapper;

    @Override
    public ResultVo addCustomer(CustomerReq customerReq,String token) {
        CheckUtils.validate(customerReq);
        EmpResp empByToken = empMapper.getEmpByToken(token);
        customerReq.setLifeCycle(1);
        int addCustomer = customerMapper.addCustomer(customerReq);
        if (addCustomer != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        CustomerRecord customerRecord = new CustomerRecord();
        customerRecord.setRecordTitle("客户添加");
        customerRecord.setRecordType("客户操作记录");
        customerRecord.setRecordTime(DateUtils.getDate());
        customerRecord.setRecordContent("客户由【"+empByToken.getEmpName()+"】添加");
        customerRecord.setCusId(customerReq.getCusId());
        customerRecord.setEmpId(empByToken.getEmpId());
        int addCustomerRecord = recordMapper.addCustomerRecord(customerRecord);
        if (addCustomerRecord != 1) {
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
        EmpResp emp = empMapper.getEmp(customer.getEmpId());
        customer.setEmpName(emp.getEmpName());
        return ResultUtils.response(customer);
    }

    @Override
    public ResultVo listCustomer(CustomerReq customerReq,String token) {
        Integer pageNum = customerReq.getPageNum();
        Integer pageSize = customerReq.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        EmpResp empByToken = empMapper.getEmpByToken(token);
        List<CustomerResp> customerResps;
        if (empByToken.getEmpName().equals("admin")) {
            customerResps = customerMapper.listCustomer(customerReq);
        } else {
            customerResps = customerMapper.listCustomerByEmpId(customerReq,empByToken.getEmpName());
        }
        customerFormat(customerResps);
        PageInfo<CustomerResp> list = new PageInfo<>(customerResps);
        return ResultUtils.response(list);
    }

    @Override
    public ResultVo addCusAndCon(CusConReq cusConReq,String token) {
        ContactsReq contactsReq = new ContactsReq();
        CustomerReq customerReq = new CustomerReq();
        EmpResp empByToken = empMapper.getEmpByToken(token);

        ClueReq clueReq = new ClueReq();
        clueReq.setClueId(cusConReq.getClueId());
        ClueResp clue = clueMapper.getClue(cusConReq.getClueId());
        clueReq.setHandlePerson(empByToken.getEmpName());
        clueReq.setClueStatus(1);
        clueReq.setUpdateTime(DateUtils.getDate());
        clueReq.setVersion(clue.getVersion());
        clueReq.setHandleResult(1);

        int editClue = clueMapper.editClue(clueReq);
        if (editClue != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }

        CustomerResp customerByName = customerMapper.getCustomerByName(cusConReq.getCusName());
        if (customerByName == null || "".equals(customerByName)) {
            customerReq.setCusName(cusConReq.getCusName());
            customerReq.setAbbreviation(cusConReq.getAbbreviation());
            customerReq.setCusDictSource(cusConReq.getCusDictSource());
            customerReq.setCusRemark(cusConReq.getCusRemark());
            customerReq.setEmpId(cusConReq.getEmpId());

            int addCustomer = customerMapper.addCustomerAndContacts(customerReq);
            if (addCustomer != 1) {
                throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                        ResultEnum.DATA_ADD_FAIL.getMessage());
            }
            System.err.println("新增后主键："+customerReq.getCusId());
        }
        contactsReq.setContactsName(cusConReq.getContactsName());
        contactsReq.setContactsPhone(cusConReq.getContactsPhone());
        contactsReq.setQq(cusConReq.getQq());
        contactsReq.setWechat(cusConReq.getWechat());
        contactsReq.setRemark(cusConReq.getCusRemark());
        if (customerByName == null || "".equals(customerByName)) {
            contactsReq.setCusId(customerReq.getCusId());
        } else {
            contactsReq.setCusId(customerByName.getCusId());
        }
        int addContacts = contactsMapper.addContactsAndCustomer(contactsReq);
        if (addContacts != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }

        ClueFollowLogResp clueFollowLogResp = new ClueFollowLogResp();
        clueFollowLogResp.setClueId(cusConReq.getClueId());
        clueFollowLogResp.setClueFollowPerson(empByToken.getEmpName());
        clueFollowLogResp.setClueFollowTitle("线索转客户");
        clueFollowLogResp.setClueFollowContent("该线索被【"+empByToken.getEmpName()+
                "】转为客户【"+cusConReq.getCusName()+"】");
        clueFollowLogResp.setClueFollowTime(DateUtils.getDate());
        int addClueFollow = clueFollowLogMapper.addClueFollow(clueFollowLogResp);
        if (addClueFollow != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }

        CustomerRecord customerRecord = new CustomerRecord();
        customerRecord.setRecordTitle("客户转换");
        customerRecord.setRecordType("客户操作记录");
        customerRecord.setRecordTime(DateUtils.getDate());
        customerRecord.setRecordContent("客户添加，由线索【"+clue.getClueName()+
                "】转换而来");
        customerRecord.setCusId(customerReq.getCusId());
        customerRecord.setEmpId(empByToken.getEmpId());
        int addCustomerRecord = recordMapper.addCustomerRecord(customerRecord);
        if (addCustomerRecord  != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        return ResultUtils.response("新增成功");
    }

    @Override
    public ResultVo listAll() {
        List<CustomerResp> customerResps = customerMapper.listAll();
        return ResultUtils.response(customerResps);
    }

    @Override
    public ResultVo insertCusAndCon(CusConReq cusConReq, String token) {
        CustomerReq customerReq = new CustomerReq();
        ContactsReq contactsReq = new ContactsReq();
        EmpResp empByToken = empMapper.getEmpByToken(token);

        customerReq.setCusName(cusConReq.getCusName());
        customerReq.setCusDictSource(cusConReq.getCusDictSource());
        customerReq.setCusRemark(cusConReq.getCusRemark());
        customerReq.setLifeCycle(1);
        customerReq.setEmpId(empByToken.getEmpId());
        int addCustomer = customerMapper.addCustomer(customerReq);
        if (addCustomer != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }

        contactsReq.setCusId(customerReq.getCusId());
        contactsReq.setEmail(cusConReq.getEmail());
        contactsReq.setContactsName(cusConReq.getContactsName());
        contactsReq.setContactsPhone(cusConReq.getContactsPhone());
        int addContacts = contactsMapper.addContacts(contactsReq);
        if (addContacts != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }

        CustomerRecord customerRecord = new CustomerRecord();
        customerRecord.setRecordTitle("客户新增");
        customerRecord.setRecordType("客户操作记录");
        customerRecord.setRecordTime(DateUtils.getDate());
        customerRecord.setRecordContent("客户添加，由员工【"+empByToken.getEmpName()+
                "】手动添加");
        customerRecord.setCusId(customerReq.getCusId());
        customerRecord.setEmpId(empByToken.getEmpId());
        int addCustomerRecord = recordMapper.addCustomerRecord(customerRecord);
        if (addCustomerRecord  != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }

        return ResultUtils.response("新增成功");
    }

    @Override
    public ResultVo newCustomerCount(CustomerReq customerReq) {
        int count = customerMapper.newCustomerCount(customerReq);
        return ResultUtils.response(count);
    }

    @Override
    public ResultVo listByName(String cusName) {
        CustomerResp customerResp = customerMapper.listByName(cusName);
        return ResultUtils.response(customerResp);
    }

    public List<CustomerResp> customerFormat(List<CustomerResp> list) {
        List<CustomerResp> customerResps = new ArrayList<>();
        for (int i=0;i<list.size();i++) {
            DictResp dict = dictMapper.getDict(list.get(i).getCusDictSource());
            list.get(i).setCusDictSourceName(dict.getDictName());
            customerResps.add(i,list.get(i));
        }
        return customerResps;
    }

}
