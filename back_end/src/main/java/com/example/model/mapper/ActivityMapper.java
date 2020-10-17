package com.example.model.mapper;

import com.example.entity.request.ActivityReq;
import com.example.entity.response.ActivityResp;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/11
 * @description:
 */
@Repository
public interface ActivityMapper {
    int addActivity(ActivityReq activityReq);
    int delActivity(Integer activityId);
    int editActivity(ActivityReq activityReq);
    ActivityResp getActivity(Integer activityId);
    List<ActivityResp> listActivity(ActivityReq activityReq);
    List<ActivityResp> listByEmp(Integer empId);
    int checkActivity(String activityTitle);
}
