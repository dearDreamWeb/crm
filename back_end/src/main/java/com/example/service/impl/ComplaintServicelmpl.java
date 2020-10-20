package com.example.service.impl;
import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.Complaint;
import com.example.model.mapper.ComplaintMapper;
import com.example.service.ComplaintService;
import com.example.util.CheckUtils;

import com.example.util.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: liuleimin
 * @date: 2020/10/14
 * @description:
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class ComplaintServicelmpl implements ComplaintService {
    @Autowired
    private ComplaintMapper complaintMapper;

    @Override
    public ResultVo addComplaint(Complaint complaint) {
        CheckUtils.validate(complaint);
        int addComplaint = complaintMapper.addComplaint(complaint);
        if (addComplaint != 1) {
            throw new SysException(ResultEnum.DICT_ADD_FAIL.getCode(),
                    ResultEnum.DICT_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addComplaint);
    }

    @Override
    public ResultVo deleteComplaint(Integer complaintId) {
        Complaint complaint = complaintMapper.getComplaint(complaintId);
        if (complaint == null) {
            throw new SysException(ResultEnum.DICT_NOT_EXIST.getCode(),
                    ResultEnum.DICT_NOT_EXIST.getMessage());
        }
        int deleteComplaint = complaintMapper.deleteComplaint(complaintId);
        if (deleteComplaint != 1) {
            throw new SysException(ResultEnum.DICT_DEL_FAIL.getCode(),
                    ResultEnum.DICT_DEL_FAIL.getMessage());
        }
        return ResultUtils.response(deleteComplaint);
    }

    @Override
    public ResultVo listComplaint(Complaint complaint) {
        Integer pageNum = complaint.getPageNum();
        Integer pageSize = complaint.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Complaint> empResps = complaintMapper.listComplaint(complaint);
        PageInfo<Complaint> list = new PageInfo<>(empResps);
        return ResultUtils.response(list);
    }

    @Override

    public ResultVo getComplaint(Integer complaintId) {
        Complaint complaint = complaintMapper.getComplaint(complaintId);
        return ResultUtils.response(complaint);
    }



    @Override
    public ResultVo updateComplaint(Complaint complaintReq) {
        Complaint complaint = complaintMapper.getComplaint(complaintReq.getComplaintId());
        if (complaint == null) {
            throw new SysException(ResultEnum.DICT_NOT_EXIST.getCode(),
                    ResultEnum.DICT_NOT_EXIST.getMessage());
        }
        int updateComplaint = complaintMapper.updateComplaint(complaintReq);
        if (updateComplaint != 1) {
            throw new SysException(ResultEnum.DICT_UPDATE_FAIL.getCode(),
                    ResultEnum.DICT_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(updateComplaint);
    }

}

