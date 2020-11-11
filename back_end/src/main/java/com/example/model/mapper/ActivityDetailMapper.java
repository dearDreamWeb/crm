package com.example.model.mapper;

import com.example.entity.request.ActivityDetailReq;
import com.example.entity.response.ActivityDetailResp;
import com.example.entity.response.EmpResp;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/10/17
 * @description:
 */
@Repository
public interface ActivityDetailMapper {
    int addActivityDetail(ActivityDetailReq activityDetailReq);
    int delActivityDetail(Integer activityDetailId);
    int editActivityDetail(ActivityDetailReq activityDetailReq);
    ActivityDetailResp getActivityDetail(Integer activityDetailId);
    List<ActivityDetailResp> listActivityDetail(ActivityDetailReq activityDetailReq);
    List<EmpResp> getEmpByActivityId(Integer activityId);
}
