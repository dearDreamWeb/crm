package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.CustomerRecord;
import com.example.entity.ResultVo;
import com.example.entity.SanGuest;
import com.example.entity.request.FollowLogReq;
import com.example.entity.response.CustomerResp;
import com.example.entity.response.EmpResp;
import com.example.entity.response.FollowLogResp;
import com.example.model.mapper.*;
import com.example.service.FollowLogService;
import com.example.util.CheckUtils;
import com.example.util.DateUtils;
import com.example.util.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/26
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FollowLogServiceImpl implements FollowLogService {
    @Autowired
    private FollowLogMapper logMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private SanGuestMapper sanyGuestMapper;

    @Autowired
    private CustomerRecordMapper recordMapper;

    @Override
    public ResultVo addFollow(FollowLogReq followLogReq,String token) {
        CheckUtils.validate(followLogReq);
        if (followLogReq.getFollowPid() == null) {
            followLogReq.setFollowPid(0);
        }
        int addFollow = logMapper.addFollow(followLogReq);
        if (addFollow != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        Integer saleId = followLogReq.getSaleId();
        EmpResp empByToken = empMapper.getEmpByToken(token);
        CustomerResp customer = customerMapper.getBySaleId(saleId);

        SanGuest san = new SanGuest();
        san.setSanGuestName("识别接触");
        san.setSanGuestTime(DateUtils.getDate());
        san.setCusId(customer.getCusId());
        san.setSanGuestEmpId(empByToken.getEmpId());
        int addSanyGuest = sanyGuestMapper.addSanGuest(san);
        if (addSanyGuest != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }

        CustomerRecord customerRecord = new CustomerRecord();
        customerRecord.setRecordTitle("三一客推进");
        customerRecord.setRecordType("三一客");
        customerRecord.setRecordTime(DateUtils.getDate());
        customerRecord.setCusId(customer.getCusId());
        customerRecord.setRecordContent("员工"+empByToken.getEmpName()+
                "对【"+customer.getCusName()+"】的三一节点推进");
        customerRecord.setEmpId(empByToken.getEmpId());
        int addCustomerRecord = recordMapper.addCustomerRecord(customerRecord);
        if (addCustomerRecord != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }

        return ResultUtils.response(addFollow);
    }

    @Override
    public ResultVo delFollow(FollowLogReq followLogReq) {
        Integer followId = followLogReq.getFollowId();
        FollowLogResp follow = logMapper.getFollow(followId);
        if (follow == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        int delFollow = logMapper.delFollow(followId);
        if (delFollow != 1) {
            throw new SysException(ResultEnum.DATA_DEL_FAIL.getCode(),
                    ResultEnum.DATA_DEL_FAIL.getMessage());
        }
        return ResultUtils.response(delFollow);
    }

    @Override
    public ResultVo editFollow(FollowLogReq followLogReq) {
        Integer followId = followLogReq.getFollowId();
        FollowLogResp follow = logMapper.getFollow(followId);
        if (follow == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        followLogReq.setVersion(follow.getVersion());
        followLogReq.setUpdateTime(DateUtils.getDate());
        int editFollow = logMapper.editFollow(followLogReq);
        if (editFollow != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(editFollow);
    }

    @Override
    public ResultVo getFollow(Integer followId) {
        FollowLogResp follow = logMapper.getFollow(followId);
        if (follow == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        return ResultUtils.response(follow);
    }

    @Override
    public ResultVo listFollow(FollowLogReq followLogReq) {
        Integer pageNum = followLogReq.getPageNum();
        Integer pageSize = followLogReq.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<FollowLogResp> followLogResps = logMapper.listFollow(followLogReq);
        PageInfo<FollowLogResp> list = new PageInfo<>(followLogResps);
        return ResultUtils.response(list);
    }

    @Override
    public ResultVo listPidFollow() {
        List<FollowLogResp> followLogResps = logMapper.listPidFollow();
        return ResultUtils.response(followLogResps);
    }

    @Override
    public ResultVo listBySale(FollowLogReq followLogReq) {
        List<FollowLogResp> followLogResps = logMapper.listFollow(followLogReq);
        return ResultUtils.response(followLogResps);
    }
}
