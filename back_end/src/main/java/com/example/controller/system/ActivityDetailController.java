package com.example.controller.system;

import com.example.anno.CheckPermissions;
import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.ActivityDetailReq;
import com.example.service.ActivityDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
