package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.SzReceivablePlan;
import com.example.entity.request.SzReceivableRecord;
import com.example.model.mapper.SzReceivablePlanMapper;
import com.example.model.mapper.SzReceivableRecordMapper;
import com.example.service.SzReceivablePlanService;
import com.example.util.DateUtils;
import com.example.util.MyUtils;
import com.example.util.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Autowired
    private SzReceivableRecordMapper szrecordMapper;

    @Override
    public ResultVo addszPlan(SzReceivablePlan szplan) {
        szplan.setPlanCaozuotime(DateUtils.getDate());
        szplan.setPlanDel(0);//(删除)否0
        szplanMapper.addszPlan(szplan);//调用szplan的新增方法
        //获取回款计划期次
        Integer period= szplan.getPlanPeriod();
        //批量新增从表
        List<SzReceivableRecord> list=new ArrayList<>(period);
        System.out.println(szplan.getPlanCaozuopeople());
        if (szplan.getPlanId() != null){
            SzReceivablePlan plan=szplanMapper.getszPlan(szplan.getPlanId());
            System.out.println("***2.plan****:"+plan);
            szplan.setPlanPeriod(szplan.getPlanPeriod());
            for (int i =0;i<period;i++){
                SzReceivableRecord record = new SzReceivableRecord();
                System.out.println("****333.**."+szplan.getSzReceivableRecorde());
                // 给从表 记录表赋值
                record.setPlanId(szplan.getPlanId());
               // record.setRecoLiushui(MyUtils.record());
                record.setRecoReceivable(0);
                record.setMoneyPlan(szplan.getSzReceivableRecorde().get(i).getMoneyPlan());
                record.setRecordPlan(szplan.getSzReceivableRecorde().get(i).getRecordPlan());
                record.setTimePlan(szplan.getSzReceivableRecorde().get(i).getTimePlan());
                list.add(i,record);
                System.out.println("详情请字段："+record);
            }
            szrecordMapper.addPlanANDReco(list);
        }
        return ResultUtils.response("新增成功");
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
