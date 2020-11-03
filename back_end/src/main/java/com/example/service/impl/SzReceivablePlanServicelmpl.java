package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.SzReceivablePlan;
import com.example.model.mapper.SzReceivablePlanMapper;
import com.example.service.SzReceivablePlanService;
import com.example.util.DateUtils;
import com.example.util.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: yangqianqian
 * @date: 2020/10/28
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SzReceivablePlanServicelmpl implements SzReceivablePlanService {
    @Autowired
    private SzReceivablePlanMapper szplanMapper;

    @Override
    public ResultVo addszPlan(SzReceivablePlan szplan) {
        szplan.setPlanCaozuotime(DateUtils.getDate());
        int addszPlan=szplanMapper.addszPlan(szplan);
        if (addszPlan != 1){
            throw new SysException(ResultEnum.ReceivablePlan_ADD_FAIL.getCode(),
                    ResultEnum.ReceivablePlan_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addszPlan);
    }

    @Override
    public ResultVo delszPlan(SzReceivablePlan szplan) {
        Integer planId=szplan.getPlanId();
        int delszPlan=szplanMapper.delszPlan(planId);
        if (delszPlan != 1){
            throw new SysException(ResultEnum.ReceivablePlan_DEL_FAIL.getCode(),
                    ResultEnum.ReceivablePlan_DEL_FAIL.getMessage());
        }
        szplanMapper.delszPlan(planId);
        return ResultUtils.response(delszPlan);
    }

    @Override
    public ResultVo editszPlan(SzReceivablePlan szplan) {
       SzReceivablePlan deptResp=szplanMapper.getszPlan(szplan.getPlanId());
       if (deptResp == null){
           throw new SysException(ResultEnum.ReceivablePlan_DEL_FAIL.getCode(),
                   ResultEnum.ReceivablePlan_DEL_FAIL.getMessage());
       }
       int editszPlan =szplanMapper.editszPlan(szplan);
       if (editszPlan != 1){
           throw new SysException(ResultEnum.ReceivablePlan_DEL_FAIL.getCode(),
                   ResultEnum.ReceivablePlan_DEL_FAIL.getMessage());
       }
       return ResultUtils.response(editszPlan);
    }

    @Override
    public ResultVo getszPlan(Integer planId) {
        SzReceivablePlan szplan =szplanMapper.getszPlan(planId);
        return ResultUtils.response(szplan);
    }

    @Override
    public ResultVo listPlan(SzReceivablePlan szplan) {
        Integer pageNum=szplan.getPageNum();
        Integer pageSize=szplan.getPageSize();
        if (pageNum == null ){
            pageNum = 1;
        }
        if (pageSize == null){
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<SzReceivablePlan> deptResps=szplanMapper.listszPlan(szplan);
        PageInfo<SzReceivablePlan> list = new PageInfo<>(deptResps);
        return ResultUtils.response(list);
    }
}
