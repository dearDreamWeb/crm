package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.SzReceivableRecord;
import com.example.model.mapper.SzReceivableRecordMapper;
import com.example.service.SzReceivableRecordService;
import com.example.util.ResultUtils;
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

    @Override
    public ResultVo getszReco(Integer recoId) {
        SzReceivableRecord record=szRecordMapper.getszReco(recoId);
        return ResultUtils.response(record);
    }

    @Override
    public ResultVo editszRecoHas(SzReceivableRecord record) {
        System.out.println("11");
        System.out.println(record.getRecoMoney());
        System.out.println(record.getRecoHasmoney());
        SzReceivableRecord deptResp = szRecordMapper.getszReco(record.getRecoId());
        if (deptResp == null) {
            throw new SysException(ResultEnum.ReceivableRecord_UPDATE_FAIL.getCode(),
                    ResultEnum.ReceivableRecord_UPDATE_FAIL.getMessage());
        }
        int editszRecoHas = szRecordMapper.editszRecoHas(record);
        if (editszRecoHas <1) {
            throw new SysException(ResultEnum.ReceivableRecord_UPDATE_FAIL.getCode(),
                    ResultEnum.ReceivableRecord_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(editszRecoHas);
    }
}
