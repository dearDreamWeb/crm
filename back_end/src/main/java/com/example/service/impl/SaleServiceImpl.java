package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.CustomerRecord;
import com.example.entity.ResultVo;
import com.example.entity.SaleDetailDemand;
import com.example.entity.request.DemandReq;
import com.example.entity.request.SaleDetailReq;
import com.example.entity.request.SaleReq;
import com.example.entity.response.*;
import com.example.model.mapper.*;
import com.example.service.SaleService;
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
 * @date: 2020/11/2
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleMapper saleMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private SaleDetailMapper detailMapper;

    @Autowired
    private DemandMapper demandMapper;

    @Autowired
    private CustomerRecordMapper recordMapper;

    @Autowired
    private ContactsMapper contactsMapper;

    @Override
    public ResultVo addSale(SaleReq saleReq,String token) {
        CheckUtils.validate(saleReq);
        EmpResp empByToken = empMapper.getEmpByToken(token);
        saleReq.setEmpId(empByToken.getEmpId());
        int addSale = saleMapper.addSale(saleReq);
        if (addSale != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }

        CustomerResp customer = customerMapper.getCustomer(saleReq.getCusId());

        CustomerRecord customerRecord = new CustomerRecord();
        customerRecord.setRecordTitle("添加销售机会");
        customerRecord.setRecordContent("员工【"+empByToken.getEmpName()+"】对【"+
                customer.getCusName()+"】添加销售机会");
        customerRecord.setEmpId(empByToken.getEmpId());
        customerRecord.setCusId(saleReq.getCusId());
        customerRecord.setRecordType("销售机会");
        customerRecord.setRecordTime(DateUtils.getDate());
        int addCustomerRecord = recordMapper.addCustomerRecord(customerRecord);
        if (addCustomerRecord != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }

        return ResultUtils.response(addSale);
    }

    @Override
    public ResultVo delSale(SaleReq saleReq) {
        Integer saleId = saleReq.getSaleId();
        if (saleId == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        int delSale = saleMapper.delSale(saleId);
        if (delSale != 1) {
            throw new SysException(ResultEnum.DATA_DEL_FAIL.getCode(),
                    ResultEnum.DATA_DEL_FAIL.getMessage());
        }
        return ResultUtils.response(delSale);
    }

    @Override
    public ResultVo editSale(SaleReq saleReq) {
        Integer saleId = saleReq.getSaleId();
        if (saleId == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        SaleResp sale = saleMapper.getSale(saleId);
        if (sale == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        saleReq.setVersion(sale.getVersion());
        saleReq.setUpdateTime(DateUtils.getDate());
        int editSale = saleMapper.editSale(saleReq);
        if (editSale != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(editSale);
    }

    @Override
    public ResultVo getSale(SaleReq saleReq) {
        Integer saleId = saleReq.getSaleId();
        SaleResp sale = saleMapper.getSale(saleId);
        CustomerResp customer = customerMapper.getCustomer(sale.getCusId());
        ContactsResp contacts = contactsMapper.getContacts(sale.getContactsId());
        EmpResp emp = empMapper.getEmp(sale.getEmpId());
        sale.setCusName(customer.getCusName());
        sale.setContactsName(contacts.getContactsName());
        sale.setEmpName(emp.getEmpName());
        if (sale == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        return ResultUtils.response(sale);
    }

    @Override
    public ResultVo listSale(SaleReq saleReq) {
        Integer pageNum = saleReq.getPageNum();
        Integer pageSize = saleReq.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<SaleResp> saleResps = saleMapper.listSale(saleReq);
        for (int i=0;i<saleResps.size();i++) {
            SaleResp saleResp = saleResps.get(i);
            EmpResp emp = empMapper.getEmp(saleResp.getEmpId());
            CustomerResp customer = customerMapper.getCustomer(saleResp.getCusId());
            saleResp.setCusName(customer.getCusName());
            if (emp != null) {
                saleResp.setEmpName(emp.getEmpName());
            } else {
                saleResp.setEmpName(null);
            }
        }
        PageInfo<SaleResp> list = new PageInfo<>(saleResps);
        return ResultUtils.response(list);
    }

    @Override
    public ResultVo addSaleDetailDemand(SaleDetailDemand saleDetailDemand,String token) {
        SaleReq saleReq = new SaleReq();
        SaleDetailReq saleDetailReq = new SaleDetailReq();
        DemandReq demandReq = new DemandReq();
        EmpResp empByToken = empMapper.getEmpByToken(token);

        saleReq.setSaleName(saleDetailDemand.getSaleName());
        saleReq.setSaleStatus(saleDetailDemand.getSaleStatus());
        saleReq.setCusId(saleDetailDemand.getCusId());
        saleReq.setContactsId(saleDetailDemand.getContactsId());
        saleReq.setDiscoveryTime(saleDetailDemand.getDiscoveryTime());
        saleReq.setSaleType(saleDetailDemand.getSaleType());
        saleReq.setEmpId(empByToken.getEmpId());
        int addSale = saleMapper.addSale(saleReq);
        Integer saleId = saleReq.getSaleId();
        if (addSale != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        saleDetailReq.setSaleId(saleId);
        if (saleDetailDemand.getDemandContent() != null
                && !"".equals(saleDetailDemand.getDemandContent())) {
            saleDetailReq.setSaleStage("需求分析");
        } else {
            saleDetailReq.setSaleStage("初期沟通");
        }
        saleDetailReq.setSaleStarBeacon(saleDetailDemand.getSaleStarBeacon());
        saleDetailReq.setSalePriorLevel(saleDetailDemand.getSalePriorLevel());
        int addSaleDetail = detailMapper.addSaleDetail(saleDetailReq);
        if (addSaleDetail != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        if (saleDetailDemand.getDemandContent() != null
                && !"".equals(saleDetailDemand.getDemandContent())) {
            CustomerResp customer = customerMapper.getCustomer(saleDetailDemand.getCusId());
            demandReq.setDemandTitle(DateUtils.dateToDateStr(DateUtils.getDate())+
                    customer.getCusName()+"需求");
            demandReq.setDemandContent(saleDetailDemand.getDemandContent());
            demandReq.setSaleId(saleId);
            demandReq.setDemandDegree(degreeRating(saleDetailDemand.getSalePriorLevel()));
            demandReq.setEmpId(empByToken.getEmpId());
            demandReq.setCusId(saleDetailDemand.getCusId());
            demandReq.setContactsId(saleDetailDemand.getContactsId());
            int addDemand = demandMapper.addDemand(demandReq);
            if (addDemand != 1) {
                throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                        ResultEnum.DATA_ADD_FAIL.getMessage());
            }
        }

        return ResultUtils.response("新增成功");
    }

    @Override
    public ResultVo editSaleAndDetail(SaleDetailDemand saleDetailDemand) {
        Integer saleId = saleDetailDemand.getSaleId();
        SaleResp sale = saleMapper.getSale(saleId);
        if (sale == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }

        SaleReq saleReq = new SaleReq();
        saleReq.setSaleId(saleDetailDemand.getSaleId());
        saleReq.setSaleName(saleDetailDemand.getSaleName());
        saleReq.setSaleStatus(saleDetailDemand.getSaleStatus());
        saleReq.setSaleType(saleDetailDemand.getSaleType());
        saleReq.setVersion(sale.getVersion());
        saleReq.setUpdateTime(DateUtils.getDate());
        int editSale = saleMapper.editSale(saleReq);
        if (editSale != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }

        SaleDetailResp saleDetailResp = sale.getSaleDetailResp();
        SaleDetailReq saleDetailReq = new SaleDetailReq();

        saleDetailReq.setSaleDetailId(saleDetailResp.getSaleDetailId());
        saleDetailReq.setSaleEstimateDate(saleDetailDemand.getSaleEstimateDate());
        saleDetailReq.setSaleExpectMoney(saleDetailDemand.getSaleExpectMoney());
        saleDetailReq.setSalePossibility(saleDetailDemand.getSalePossibility());
        saleDetailReq.setSaleStage(saleDetailDemand.getSaleStage());
        saleDetailReq.setSaleStarBeacon(saleDetailDemand.getSaleStarBeacon());
        saleDetailReq.setSalePriorLevel(saleDetailDemand.getSalePriorLevel());
        saleDetailReq.setVersion(saleDetailResp.getVersion());
        saleDetailReq.setUpdateTime(DateUtils.getDate());
        int editSaleDetail = detailMapper.editSaleDetail(saleDetailReq);
        if (editSaleDetail != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response("修改成功");
    }

    @Override
    public ResultVo selectSaleAndDemandAndSolution(SaleReq saleReq) {
        List<SaleResp> saleResps = saleMapper.selectSaleAndDemandAndSolution(saleReq);
        return ResultUtils.response(saleResps);
    }

    public static String degreeRating(String degree) {
        if (degree.equals("一级") || degree.equals("二级")) {
            return "非常重要";
        } else if (degree.equals("三级") || degree.equals("四级")) {
            return "重要";
        } else {
            return "一般";
        }
    }
}
