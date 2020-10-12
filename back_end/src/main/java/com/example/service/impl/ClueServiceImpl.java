package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.ClueReq;
import com.example.entity.response.ActivityResp;
import com.example.entity.response.ClueResp;
import com.example.entity.response.EmpResp;
import com.example.model.mapper.ActivityMapper;
import com.example.model.mapper.ClueMapper;
import com.example.model.mapper.EmpMapper;
import com.example.service.ClueService;
import com.example.util.CheckUtils;
import com.example.util.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/12
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueMapper clueMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public ResultVo addClue(ClueReq clueReq) {
        CheckUtils.validate(clueReq);
        int addClue = clueMapper.addClue(clueReq);
        if (addClue != 1) {
            throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                    ResultEnum.DATA_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addClue);
    }

    @Override
    public ResultVo delClue(ClueReq clueReq) {
        Integer clueId = clueReq.getClueId();
        ClueResp clue = clueMapper.getClue(clueId);
        if (clue == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        int delClue = clueMapper.delClue(clueId);
        if (delClue != 1) {
            throw new SysException(ResultEnum.DATA_DEL_FAIL.getCode(),
                    ResultEnum.DATA_DEL_FAIL.getMessage());
        }
        return ResultUtils.response(delClue);
    }

    @Override
    public ResultVo editClue(ClueReq clueReq) {
        ClueResp clue = clueMapper.getClue(clueReq.getClueId());
        if (clue == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        int editClue = clueMapper.editClue(clueReq);
        if (editClue != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(editClue);
    }

    @Override
    public ResultVo getClue(Integer clueId) {
        ClueResp clue = clueMapper.getClue(clueId);
        Integer activityId = clue.getActivityId();
        Integer empId = clue.getEmpId();
        ActivityResp activity = activityMapper.getActivity(activityId);
        EmpResp emp = empMapper.getEmp(empId);
        clue.setActivityResp(activity);
        clue.setEmpResp(emp);
        return ResultUtils.response(clue);
    }

    @Override
    public ResultVo listClue(ClueReq clueReq) {
        Integer pageNum = clueReq.getPageNum();
        Integer pageSize = clueReq.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<ClueResp> clueResps = clueMapper.listClue(clueReq);
        PageInfo<ClueResp> list = new PageInfo<>(clueResps);
        return ResultUtils.response(list);
    }

}
