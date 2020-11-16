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
    @PostMapping("/edit")
    public ResultVo editszReco(@RequestBody SzReceivableRecord record){
        return szReceivableRecordService.editszReco(record);
    }
}
