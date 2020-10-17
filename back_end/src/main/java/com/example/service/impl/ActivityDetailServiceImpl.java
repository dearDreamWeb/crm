package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.ActivityDetailReq;
import com.example.entity.response.ActivityDetailResp;
import com.example.model.mapper.ActivityDetailMapper;
import com.example.service.ActivityDetailService;
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
 * @date: 2020/10/17
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityDetailServiceImpl implements ActivityDetailService {
    @Autowired
    private ActivityDetailMapper detailMapper;

    @Override
    public ResultVo addActivityDetail(ActivityDetailReq activityDetailReq) {
        Integer activityId = activityDetailReq.getActivityId();
        List<Integer> empIdList = activityDetailReq.getEmpIdList();
        for (int i=0;i<empIdList.size();i++) {
            String url = generateUrl(activityId, empIdList.get(i));
            activityDetailReq.setUrl(url);
            activityDetailReq.setEmpId(empIdList.get(i));
            activityDetailReq.setAuthDate(DateUtils.getDate());
            int addActivityDetail = detailMapper.addActivityDetail(activityDetailReq);
            if (addActivityDetail != 1) {
                throw new SysException(ResultEnum.DATA_ADD_FAIL.getCode(),
                        ResultEnum.DATA_ADD_FAIL.getMessage());
            }
        }
        return ResultUtils.response("数据添加成功");
    }

    @Override
    public ResultVo delActivityDetail(ActivityDetailReq activityDetailReq) {
        Integer activityDetailId = activityDetailReq.getActivityDetailId();
        if (activityDetailId == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        int delActivityDetail = detailMapper.delActivityDetail(activityDetailId);
        if (delActivityDetail != 1) {
            throw new SysException(ResultEnum.DATA_DEL_FAIL.getCode(),
                    ResultEnum.DATA_DEL_FAIL.getMessage());
        }
        return ResultUtils.response(delActivityDetail);
    }

    @Override
    public ResultVo editActivityDetail(ActivityDetailReq activityDetailReq) {
        Integer activityDetailId = activityDetailReq.getActivityDetailId();
        ActivityDetailResp activityDetail = detailMapper.getActivityDetail(activityDetailId);
        if (activityDetail == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        activityDetailReq.setVersion(activityDetail.getVersion());
        activityDetailReq.setUpdateTime(DateUtils.getDate());
        int editActivityDetail = detailMapper.editActivityDetail(activityDetailReq);
        if (editActivityDetail != 1) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(editActivityDetail);
    }

    @Override
    public ResultVo getActivityDetail(ActivityDetailReq activityDetailReq) {
        Integer activityDetailId = activityDetailReq.getActivityDetailId();
        if (activityDetailId == null) {
            throw new SysException(ResultEnum.DATA_NOT_EXIST.getCode(),
                    ResultEnum.DATA_NOT_EXIST.getMessage());
        }
        ActivityDetailResp activityDetail = detailMapper.getActivityDetail(activityDetailId);
        if (activityDetail == null) {
            throw new SysException(ResultEnum.DATA_UPDATE_FAIL.getCode(),
                    ResultEnum.DATA_UPDATE_FAIL.getMessage());
        }
        activityDetail.setActivityTitle(activityDetail.getActivityResp().getActivityTitle());
        activityDetail.setEmpName(activityDetail.getEmpResp().getEmpName());
        return ResultUtils.response(activityDetail);
    }

    @Override
    public ResultVo listActivityDetail(ActivityDetailReq activityDetailReq) {
        Integer pageNum = activityDetailReq.getPageNum();
        Integer pageSize = activityDetailReq.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<ActivityDetailResp> activityDetailResps = detailMapper.listActivityDetail(activityDetailReq);
        for (int i=0;i<activityDetailResps.size();i++) {
            activityDetailResps.get(i).setActivityTitle(activityDetailResps.get(i).getActivityResp().getActivityTitle());
            activityDetailResps.get(i).setEmpName(activityDetailResps.get(i).getEmpResp().getEmpName());
        }
        PageInfo<ActivityDetailResp> list = new PageInfo<>(activityDetailResps);
        return ResultUtils.response(list);
    }

    public static String generateUrl(Integer activityId,Integer empId) {
        String url = "";
        if (activityId != null && empId != null) {
             url = "http://localhost:8080/clue_page?activityId=" + activityId + "&empId=" + empId;
        }
        return url;
    }
}
