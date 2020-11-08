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

   /*    int addszPlan=szplanMapper.addszPlan(szplan);*/

        //获取回款计划期次
        Integer period= szplan.getPlanPeriod();
        List<SzReceivableRecord> list=new ArrayList<>(period);
        System.out.println("1.List<SzReceivableRecord>:"+list);
        if (szplan.getPlanId() != null){
            SzReceivablePlan plan=szplanMapper.getszPlan(szplan.getPlanId());
            System.out.println("2.plan:"+plan);
            szplan.setPlanPeriod(szplan.getPlanPeriod());
            for (int i =0;i<period;i++){
                SzReceivableRecord record = new SzReceivableRecord();
                System.out.println("3.record"+record);
                record.setPlanId(szplan.getPlanId());
                record.setRecoLiushui(MyUtils.record());
                list.add(i,record);
            }
            szrecordMapper.addPlanANDReco(list);
        }

       /* //获取回款记录的信息
        List<SzReceivableRecord> records=szplan.getSzReceivableRecorde();
        //调用mapper方法，新增单条
        szplanMapper.addszPlan(szplan);
        //获取新增计划编号
        Integer planid=szplan.getPlanId();
        System.out.println("plan主键编号是："+planid);
        //4.通过循环逐条递增回款记录
        for (SzReceivableRecord record :records){
            System.out.println("进入for循环啦~~~");
            record.setPlanId(planid);
            //5.调用记录的新增方法
            szrecordMapper.addPlanANDReco(record);
        }*/
        /*if (addszPlan != 1){
            throw new SysException(ResultEnum.ReceivablePlan_ADD_FAIL.getCode(),
                    ResultEnum.ReceivablePlan_ADD_FAIL.getMessage());
        }*/
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
