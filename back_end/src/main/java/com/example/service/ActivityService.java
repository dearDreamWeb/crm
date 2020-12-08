package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.ActivityReq;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/11
 * @description:
 */
public interface ActivityService {
    ResultVo addActivity(ActivityReq activityReq);
    ResultVo delActivity(ActivityReq activityReq);
    ResultVo editActivity(ActivityReq activityReq);
    ResultVo getActivity(Integer activityId);
    ResultVo listActivity(ActivityReq activityReq);
    ResultVo listByEmp(Integer empId);
    ResultVo addViews(Integer activityId);
    ResultVo pageViewCount();
    ResultVo listPage(ActivityReq activityReq);
}
