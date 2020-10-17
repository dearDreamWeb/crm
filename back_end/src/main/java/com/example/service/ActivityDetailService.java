package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.ActivityDetailReq;

/**
 * @author: pengjia
 * @date: 2020/10/17
 * @description:
 */
public interface ActivityDetailService {
    ResultVo addActivityDetail(ActivityDetailReq activityDetailReq);
    ResultVo delActivityDetail(ActivityDetailReq activityDetailReq);
    ResultVo editActivityDetail(ActivityDetailReq activityDetailReq);
    ResultVo getActivityDetail(ActivityDetailReq activityDetailReq);
    ResultVo listActivityDetail(ActivityDetailReq activityDetailReq);
}
