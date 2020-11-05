package com.example.service.impl;

import com.example.entity.request.SzReceivableRecord;
import com.example.model.mapper.SzReceivableRecordMapper;
import com.example.service.SzReceivableRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: yangqianqian
 * @date: 2020/11/5
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SzReceivableRecordServicelmpl implements SzReceivableRecordService {
    @Autowired
    SzReceivableRecordMapper szRecordMapper;

    @Override
    public List<SzReceivableRecord> listPlanANDReco(Integer recoId) {
        return szRecordMapper.listPlanANDReco(recoId);
    }
}
