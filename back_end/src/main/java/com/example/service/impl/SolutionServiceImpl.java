package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.SolutionReq;
import com.example.entity.response.CustomerResp;
import com.example.entity.response.DemandResp;
import com.example.entity.response.SaleResp;
import com.example.entity.response.SolutionResp;
import com.example.model.mapper.CustomerMapper;
import com.example.model.mapper.DemandMapper;
import com.example.model.mapper.SaleMapper;
import com.example.model.mapper.SolutionMapper;
import com.example.service.SolutionService;
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
 * @date: 2020/11/2
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SolutionServiceImpl implements SolutionService {
    @Autowired
    private SolutionMapper solutionMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private SaleMapper saleMapper;

    @Autowired
    private DemandMapper demandMapper;

    @Override
    public ResultVo addSolution(SolutionReq solutionReq) {
        CheckUtils.validate(solutionReq);
        int addSolution = solutionMapper.addSolution(solutionReq);
        if (addSolution != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addSolution);
    }

    @Override
    public ResultVo delSolution(SolutionReq solutionReq) {
        Integer solutionId = solutionReq.getSolutionId();
        SolutionResp solution = solutionMapper.getSolution(solutionId);
        if (solution == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        int delSolution = solutionMapper.delSolution(solutionId);
        if (delSolution != 1) {
            throw new SysException(ResultEnum.DATA_DEL_FAIL.getCode(),
                    ResultEnum.DATA_DEL_FAIL.getMessage());
        }
        return ResultUtils.response(delSolution);
    }

    @Override
    public ResultVo editSolution(SolutionReq solutionReq) {
        Integer solutionId = solutionReq.getSolutionId();
        SolutionResp solution = solutionMapper.getSolution(solutionId);
        if (solution == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        solutionReq.setVersion(solution.getVersion());
        solutionReq.setUpdateTime(DateUtils.getDate());
        int editSolution = solutionMapper.editSolution(solutionReq);
        if (editSolution != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(editSolution);
    }

    @Override
    public ResultVo getSolution(SolutionReq solutionReq) {
        Integer solutionId = solutionReq.getSolutionId();
        SolutionResp solution = solutionMapper.getSolution(solutionId);
        if (solution == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        return ResultUtils.response(solution);
    }

    @Override
    public ResultVo listSolution(SolutionReq solutionReq) {
        Integer pageNum = solutionReq.getPageNum();
        Integer pageSize = solutionReq.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<SolutionResp> solutionResps = solutionMapper.listSolution(solutionReq);
        extractMethod(solutionResps);
        PageInfo<SolutionResp> list = new PageInfo<>(solutionResps);
        return ResultUtils.response(list);
    }

    public List<SolutionResp> extractMethod(List<SolutionResp> list) {
        List<SolutionResp> solutionResps = new ArrayList<>();
        for (SolutionResp solutionResp : list) {
            CustomerResp customer = customerMapper.getCustomer(solutionResp.getCusId());
            SaleResp sale = saleMapper.getSale(solutionResp.getSaleId());
            DemandResp demand = demandMapper.getDemand(solutionResp.getDemandId());
            if (customer != null) {
                solutionResp.setCusName(customer.getCusName());
            } else {
                solutionResp.setCusName(null);
            }
            if (sale != null) {
                solutionResp.setSaleName(sale.getSaleName());
            } else {
                solutionResp.setSaleName(null);
            }
            if (demand != null) {
                solutionResp.setDemandTitle(demand.getDemandTitle());
            } else {
                solutionResp.setDemandTitle(null);
            }
            solutionResps.add(solutionResp);
        }
        return solutionResps;
    }
}
