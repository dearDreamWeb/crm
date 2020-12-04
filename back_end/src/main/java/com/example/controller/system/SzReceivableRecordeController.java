package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.SzReceivableRecord;
import com.example.model.mapper.SzReceivableRecordMapper;
import com.example.service.SzReceivableRecordService;
import com.example.service.impl.SzReceivableRecordServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: yangqianqian
 * @date: 2020/11/11
 * @description:
 */
@RestController
@RequestMapping("/record")
public class SzReceivableRecordeController{
    @Autowired
    private SzReceivableRecordServicelmpl recordServicelmpl;
    @Autowired
    private SzReceivableRecordService szReceivableRecordService;

    @RequestMapping("/all")
    public List<SzReceivableRecord> listPlanANDReco(Integer recoId){
        System.out.println("查看详情记录~~~");
        return recordServicelmpl.listPlanANDReco(recoId);
    }
    /*记录单条*/
    @GetMapping
    public ResultVo getszReco(Integer recoId){
        return szReceivableRecordService.getszReco(recoId);
    }

    @SysLog("修改订单")
    @PostMapping("/edit_has")
    public ResultVo editszRecoHas(@RequestBody SzReceivableRecord record){
        return szReceivableRecordService.editszRecoHas(record);
    }

    @SysLog("修改订单2")
    @PostMapping("/edit_has2")
    public List<SzReceivableRecord> editszRecoHas2(@RequestBody SzReceivableRecord record){
        System.out.println("我是接受"+record);
        ResultVo vo = szReceivableRecordService.editszRecoHas(record);
        System.out.println("修改的结果是："+vo);
        System.out.println(record.getSzReceivablePlan());
        if (vo.getCode() == 20000){
            System.out.println("修改计划回款planId:"+record.getPlanId());
            return recordServicelmpl.listPlanANDReco(record.getPlanId());
        }else {
            System.out.println("修改失败");
            return null;
        }
        /*
        * int i = 调用修改的方法
        * if(i>=1){
        *   return recordServicelmpl.listPlanANDReco(recoId);
        * }else{
        *   return null
        * }
        *
        * */
    }
}
