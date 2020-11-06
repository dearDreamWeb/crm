package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.SaleDetailDemand;
import com.example.entity.request.DemandReq;
import com.example.entity.request.SaleDetailReq;
import com.example.entity.request.SaleReq;
import com.example.entity.response.CustomerResp;
import com.example.entity.response.EmpResp;
import com.example.entity.response.SaleDetailResp;
import com.example.entity.response.SaleResp;
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

    @Override
    public ResultVo addSale(SaleReq saleReq) {
        CheckUtils.validate(saleReq);
        int addSale = saleMapper.addSale(saleReq);
        if (addSale != 1) {
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
            saleResp.setEmpName(emp.getEmpName());
        }
        PageInfo<SaleResp> list = new PageInfo<>(saleResps);
        return ResultUtils.response(list);
    }

    @Override
    public ResultVo addSaleDetailDemand(SaleDetailDemand saleDetailDemand) {
        SaleReq saleReq = new SaleReq();
        SaleDetailReq saleDetailReq = new SaleDetailReq();
        DemandReq demandReq = new DemandReq();

        saleReq.setSaleName(saleDetailDemand.getSaleName());
        saleReq.setSaleStatus(saleDetailDemand.getSaleStatus());
        saleReq.setCusId(saleDetailDemand.getCusId());
        saleReq.setContactsId(saleDetailDemand.getContactsId());
        saleReq.setSaleSource(saleDetailDemand.getSaleSource());
        saleReq.setDiscoveryTime(saleDetailDemand.getDiscoveryTime());
        saleReq.setSaleType(saleDetailDemand.getSaleType());
        saleReq.setEmpId(saleDetailDemand.getEmpId());
        int addSale = saleMapper.addSale(saleReq);
        Integer saleId = saleReq.getSaleId();
        System.out.println("新增成功的销售编号是："+saleId);
        if (addSale != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        saleDetailReq.setSaleId(saleId);
        saleDetailReq.setSaleStarBeacon(saleDetailDemand.getSaleStarBeacon());
        saleDetailReq.setSalePriorLevel(saleDetailDemand.getSalePriorLevel());
        int addSaleDetail = detailMapper.addSaleDetail(saleDetailReq);
        if (addSaleDetail != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        CustomerResp customer = customerMapper.getCustomer(saleDetailDemand.getCusId());
        demandReq.setDemandTitle(DateUtils.dateToDateStr(DateUtils.getDate())+
                customer.getCusName()+"需求");
        demandReq.setDemandContent(saleDetailDemand.getDemandContent());
        demandReq.setSaleId(saleId);
        demandReq.setDemandDegree(degreeRating(saleDetailDemand.getSalePriorLevel()));
        demandReq.setEmpId(saleDetailDemand.getEmpId());
        demandReq.setCusId(saleDetailDemand.getCusId());
        demandReq.setContactsId(saleDetailDemand.getContactsId());
        int addDemand = demandMapper.addDemand(demandReq);
        if (addDemand != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
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
        saleReq.setSaleSource(saleDetailDemand.getSaleSource());
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
