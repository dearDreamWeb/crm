package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.request.SzReceivableRecord;
import com.example.model.mapper.SzReceivableRecordMapper;
import com.example.service.impl.SzReceivableRecordServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/all")
    public List<SzReceivableRecord> listPlanANDReco(Integer recoId){
        System.out.println("查看详情记录~~~");
        return recordServicelmpl.listPlanANDReco(recoId);
    }
}
