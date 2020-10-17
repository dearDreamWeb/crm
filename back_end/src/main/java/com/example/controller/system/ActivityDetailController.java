package com.example.controller.system;

import com.example.anno.CheckPermissions;
import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.ActivityDetailReq;
import com.example.service.ActivityDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: pengjia
 * @date: 2020/10/17
 * @description:
 */
@RestController
@RequestMapping("/activity_detail")
public class ActivityDetailController {
    @Autowired
    private ActivityDetailService detailService;

    @SysLog("活动分配")
    @PostMapping("/activity_auth")
    @CheckPermissions("system:activity:auth")
    public ResultVo addActivityDetail(@RequestBody ActivityDetailReq activityDetailReq) {
        return detailService.addActivityDetail(activityDetailReq);
    }

    @SysLog("活动详情修改")
    @PostMapping("/edit")
    @CheckPermissions("system:activity_detail:edit")
    public ResultVo editActivityDetail(@RequestBody ActivityDetailReq activityDetailReq) {
        return detailService.editActivityDetail(activityDetailReq);
    }

    @SysLog("活动详情删除")
    @PostMapping("/del")
    @CheckPermissions("system:activity_detail:del")
    public ResultVo delActivityDetail(@RequestBody ActivityDetailReq activityDetailReq) {
        return detailService.delActivityDetail(activityDetailReq);
    }

    @GetMapping("/list")
    public ResultVo listActivityDetail(ActivityDetailReq activityDetailReq) {
        return detailService.listActivityDetail(activityDetailReq);
    }

    @GetMapping
    public ResultVo getActivityDetail(ActivityDetailReq activityDetailReq) {
        return detailService.getActivityDetail(activityDetailReq);
    }
}
