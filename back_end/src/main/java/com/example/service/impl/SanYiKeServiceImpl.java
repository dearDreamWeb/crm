package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.*;
import com.example.entity.response.EmpResp;
import com.example.model.mapper.*;
import com.example.service.SanYiKeService;
import com.example.util.DateUtils;
import com.example.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/11/16
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SanYiKeServiceImpl implements SanYiKeService {
    @Autowired
    private RationMapper rationMapper;

    @Autowired
    private GradingMapper gradingMapper;

    @Autowired
    private QualitativeMapper qualitativeMapper;

    @Autowired
    private CustomerRecordMapper recordMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public ResultVo addRation(Ration ration,String token) {
        EmpResp empByToken = empMapper.getEmpByToken(token);

        Ration rationByCusId = rationMapper.getRationByCusId(ration.getCusId());
        if (rationByCusId != null) {
            ration.setRationId(rationByCusId.getRationId());
            int editRation = rationMapper.editRation(ration);
            if (editRation != 1) {
                throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                        ResultEnum.DATA_ADD_FAIL.getMessage());
            }
        } else {
            ration.setRationTime(DateUtils.getDate());
            ration.setRationEmpId(empByToken.getEmpId());
            int addRation = rationMapper.addRation(ration);
            if (addRation != 1) {
                throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                        ResultEnum.DATA_ADD_FAIL.getMessage());
            }
        }

        CustomerRecord customerRecord = new CustomerRecord();
        customerRecord.setRecordTitle("三一客");
        customerRecord.setRecordType("三一客");
        customerRecord.setRecordTime(DateUtils.getDate());
        customerRecord.setRecordContent("定性："+ration.getRationName()+" "+
                empByToken.getEmpName());
        customerRecord.setEmpId(empByToken.getEmpId());
        customerRecord.setCusId(ration.getCusId());
        int addCustomerRecord = recordMapper.addCustomerRecord(customerRecord);
        if (addCustomerRecord != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }

        return ResultUtils.response("操作成功");
    }

    @Override
    public ResultVo addGrading(Grading grading,String token) {
        EmpResp empByToken = empMapper.getEmpByToken(token);

        Grading gradingByCusId = gradingMapper.getGradingByCusId(grading.getCusId());
        if (gradingByCusId != null) {
            grading.setGradingId(gradingByCusId.getGradingId());
            int editGrading = gradingMapper.editGrading(grading);
            if (editGrading != 1) {
                throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                        ResultEnum.DATA_ADD_FAIL.getMessage());
            }
        } else {
            grading.setGradingTime(DateUtils.getDate());
            grading.setGradingEmpId(empByToken.getEmpId());
            int addGrading = gradingMapper.addGrading(grading);
            if (addGrading != 1) {
                throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                        ResultEnum.DATA_ADD_FAIL.getMessage());
            }
        }

        CustomerRecord customerRecord = new CustomerRecord();
        customerRecord.setRecordTitle("三一客");
        customerRecord.setRecordType("三一客");
        customerRecord.setRecordTime(DateUtils.getDate());
        customerRecord.setRecordContent("定级："+grading.getGradingName()+" "+
                empByToken.getEmpName());
        customerRecord.setEmpId(empByToken.getEmpId());
        customerRecord.setCusId(grading.getCusId());
        int addCustomerRecord = recordMapper.addCustomerRecord(customerRecord);
        if (addCustomerRecord != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }

        return ResultUtils.response("操作成功");
    }

    @Override
    public ResultVo addQualitative(Qualitative qualitative,String token) {
        EmpResp empByToken = empMapper.getEmpByToken(token);

        Qualitative qualitativeByCusId = qualitativeMapper.getQualitativeByCusId(qualitative.getCusId());
        if (qualitativeByCusId != null) {
            qualitative.setQualitativeId(qualitativeByCusId.getQualitativeId());
            int editQualitative = qualitativeMapper.editQualitative(qualitative);
            if (editQualitative != 1) {
                throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                        ResultEnum.DATA_ADD_FAIL.getMessage());
            }
        } else {
            qualitative.setQualitativeTime(DateUtils.getDate());
            qualitative.setQualitativeEmpId(empByToken.getEmpId());
            int addQualitative = qualitativeMapper.addQualitative(qualitative);
            if (addQualitative != 1) {
                throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                        ResultEnum.DATA_ADD_FAIL.getMessage());
            }
        }

        CustomerRecord customerRecord = new CustomerRecord();
        customerRecord.setRecordTitle("三一客");
        customerRecord.setRecordType("三一客");
        customerRecord.setRecordTime(DateUtils.getDate());
        customerRecord.setRecordContent("定量："+qualitative.getQualitativeName()+" "+
                empByToken.getEmpName());
        customerRecord.setEmpId(empByToken.getEmpId());
        customerRecord.setCusId(qualitative.getCusId());
        int addCustomerRecord = recordMapper.addCustomerRecord(customerRecord);
        if (addCustomerRecord != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }

        return ResultUtils.response("操作成功");
    }
}
