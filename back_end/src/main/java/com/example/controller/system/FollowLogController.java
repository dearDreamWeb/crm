package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.FollowLogReq;
import com.example.service.FollowLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: pengjia
 * @date: 2020/10/26
 * @description:
 */
@RestController
@RequestMapping("/follow")
public class FollowLogController {
    @Autowired
    private FollowLogService logService;

    @SysLog("添加跟进日志")
    @PostMapping("/add")
    public ResultVo addFollow(@RequestBody FollowLogReq followLogReq,
                              HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        return logService.addFollow(followLogReq,token);
    }

    @SysLog("删除跟进日志")
    @PostMapping("/del")
    public ResultVo delFollow(@RequestBody FollowLogReq followLogReq) {
        return logService.delFollow(followLogReq);
    }

    @SysLog("修改跟进日志")
    @PostMapping("/edit")
    public ResultVo editFollow(@RequestBody FollowLogReq followLogReq) {
        return logService.editFollow(followLogReq);
    }

    @GetMapping
    public ResultVo getFollow(@RequestParam("followId") Integer followId) {
        return logService.getFollow(followId);
    }

    @GetMapping("/list")
    public ResultVo listFollow(FollowLogReq followLogReq) {
        return logService.listFollow(followLogReq);
    }

    @GetMapping("/list_by_start")
    public ResultVo listFollowByStartTime(FollowLogReq followLogReq) {
        return logService.listFollow(followLogReq);
    }

    @GetMapping("/list_pid")
    public ResultVo listPidFollow() {
        return logService.listPidFollow();
    }

    @GetMapping("/list_by_cus")
    public ResultVo listByCus(FollowLogReq followLogReq) {
        return logService.listByCus(followLogReq);
    }
}
