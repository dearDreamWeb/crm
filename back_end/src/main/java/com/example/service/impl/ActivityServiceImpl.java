package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.ActivityDetailReq;
import com.example.entity.request.ActivityReq;
import com.example.entity.response.ActivityDetailResp;
import com.example.entity.response.ActivityResp;
import com.example.entity.response.EmpResp;
import com.example.model.mapper.ActivityDetailMapper;
import com.example.model.mapper.ActivityMapper;
import com.example.model.mapper.EmpMapper;
import com.example.service.ActivityService;
import com.example.util.CheckUtils;
import com.example.util.DateUtils;
import com.example.util.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/11
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private ActivityDetailMapper detailMapper;


    @Override
    public ResultVo addActivity(ActivityReq activityReq) {
        CheckUtils.validate(activityReq);
        int checkActivity = activityMapper.checkActivity(activityReq.getActivityTitle());
        if (checkActivity != 0) {
            throw new SysException(ResultEnum.ACTIVITY_EXIST.getCode(),
                    ResultEnum.ACTIVITY_EXIST.getMessage());
        }
        Date starTime = null;
        Date endTime = null;
        try {
            starTime = DateUtils.activityStrToDate(activityReq.getStartTime());
            endTime = DateUtils.activityStrToDate(activityReq.getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (starTime.getTime() < endTime.getTime() &&
                starTime.getTime() == endTime.getTime()) {
            throw new SysException(ResultEnum.ACTIVITY_DATE_ERROR.getCode(),
                    ResultEnum.ACTIVITY_DATE_ERROR.getMessage());
        }
        int addActivity = activityMapper.addActivity(activityReq);
        ActivityResp activityByTitle = activityMapper.getActivityByTitle(activityReq.getActivityTitle());
        Integer empId = empMapper.getEmpIdByName(activityReq.getCreateBy());
        ActivityDetailReq activityDetailReq = new ActivityDetailReq();
        activityDetailReq.setActivityId(activityByTitle.getActivityId());
        activityDetailReq.setEmpId(empId);
        detailMapper.addOne(activityDetailReq);
        if (addActivity != 1) {
            throw new SysException(ResultEnum.ACTIVITY_ADD_FAIL.getCode(),
                    ResultEnum.ACTIVITY_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addActivity);
    }

    @Override
    public ResultVo delActivity(ActivityReq activityReq) {
        Integer activityId = activityReq.getActivityId();
        ActivityResp activity = activityMapper.getActivity(activityId);
        if (activity == null) {
            throw new SysException(ResultEnum.ACTIVITY_NOT_EXIST.getCode(),
                    ResultEnum.ACTIVITY_NOT_EXIST.getMessage());
        }
        int delActivity = activityMapper.delActivity(activityId);
        if (delActivity != 1) {
            throw new SysException(ResultEnum.ACTIVITY_DEL_FAIL.getCode(),
                    ResultEnum.ACTIVITY_DEL_FAIL.getMessage());
        }
        return ResultUtils.response(delActivity);
    }

    @Override
    public ResultVo editActivity(ActivityReq activityReq) {
        Integer activityId = activityReq.getActivityId();
        ActivityResp activity = activityMapper.getActivity(activityId);
        if (activity == null) {
            throw new SysException(ResultEnum.ACTIVITY_NOT_EXIST.getCode(),
                    ResultEnum.ACTIVITY_NOT_EXIST.getMessage());
        }
        activityReq.setVersion(activity.getVersion());
        activityReq.setUpdateTime(DateUtils.getDate());
        int editActivity = activityMapper.editActivity(activityReq);
        if (editActivity != 1) {
            throw new SysException(ResultEnum.ACTIVITY_UPDATE_FAIL.getCode(),
                    ResultEnum.ACTIVITY_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(editActivity);
    }

    @Override
    public ResultVo getActivity(Integer activityId) {
        ActivityResp activity = activityMapper.getActivity(activityId);
        List<ActivityDetailResp> activityDetailResps = activity.getActivityDetailResps();
        for (int i=0;i<activity.getActivityDetailResps().size();i++) {
            EmpResp emp = empMapper.getEmp(activityDetailResps.get(i).getEmpId());
            activityDetailResps.get(i).setEmpName(emp.getEmpName());
        }
        return ResultUtils.response(activity);
    }

    @Override
    public ResultVo listActivity(ActivityReq activityReq) {
        List<ActivityResp> activityResps = activityMapper.listActivity(activityReq);
        PageInfo<ActivityResp> list = new PageInfo<>(activityResps);
        return ResultUtils.response(list);
    }

    @Override
    public ResultVo listByEmp(Integer empId) {
        List<ActivityResp> activityResps = activityMapper.listByEmp(empId);
        if (activityResps == null && activityResps.size() < 0) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        return ResultUtils.response(activityResps);
    }

    @Override
    public ResultVo addViews(Integer activityId) {
        int addViews = activityMapper.addViews(activityId);
        return ResultUtils.response(addViews);
    }

    @Override
    public ResultVo pageViewCount() {
        int count = activityMapper.pageViewCount();
        return ResultUtils.response(count);
    }

    @Override
    public ResultVo listPage(ActivityReq activityReq) {
        Integer pageNum = activityReq.getPageNum();
        Integer pageSize = activityReq.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<ActivityResp> activityResps = activityMapper.listPage(activityReq);
        PageInfo<ActivityResp> list = new PageInfo<>(activityResps);
        return ResultUtils.response(list);
    }
}
