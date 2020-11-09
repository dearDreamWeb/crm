package com.example.controller.system;

import com.example.anno.CheckPermissions;
import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.ActivityReq;
import com.example.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: pengjia
 * @date: 2020/10/11
 * @description:
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @SysLog("添加活动")
    @PostMapping("/add")
    @CheckPermissions("system:activity:add")
    public ResultVo addActivity(@RequestBody ActivityReq activityReq) {
        return activityService.addActivity(activityReq);
    }

    @SysLog("删除活动")
    @PostMapping("/del")
    @CheckPermissions("system:activity:del")
    public ResultVo delActivity(@RequestBody ActivityReq activityReq) {
        return activityService.delActivity(activityReq);
    }

    @SysLog("编辑活动")
    @PostMapping("/edit")
    @CheckPermissions("system:activity:edit")
    public ResultVo editActivity(@RequestBody ActivityReq activityReq) {
        return activityService.editActivity(activityReq);
    }

    @GetMapping
    public ResultVo getActivity(@RequestParam("activityId")Integer activityId) {
        return activityService.getActivity(activityId);
    }

    @GetMapping("/list")
    public ResultVo listActivity(ActivityReq activityReq) {
        return activityService.listActivity(activityReq);
    }

    @PostMapping("/batch_edit")
    public ResultVo batchEdit(@RequestBody ActivityReq activityReq) {
        return null;
    }

    @GetMapping("/get_by_emp")
    public ResultVo getByEmp(@RequestParam("empId")Integer empId) {
        return activityService.listByEmp(empId);
    }

    @GetMapping("/add_views")
    public ResultVo addViews(@RequestParam("activityId") Integer activityId) {
        System.err.println(activityId);
        return activityService.addViews(activityId);
    }
}
