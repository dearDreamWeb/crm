package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.Complaint;
import com.example.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: liuleimin
 * @date: 2020/10/14
 * @description:
 */

@RestController
@RequestMapping("/complaint")
public class ComplaintController {
    @Autowired
    private ComplaintService complaintService;

    @SysLog("添加关怀")
    @PostMapping("/add")
    public ResultVo addDict(@RequestBody Complaint complaint) {
        return complaintService.addComplaint(complaint);
    }

    @SysLog("删除关怀")
    @PostMapping("/del")
    public ResultVo delDict(@RequestBody Complaint complaint) {
        return complaintService.deleteComplaint(complaint);
    }

    @SysLog("修改关怀")
    @PostMapping("/update")
    public ResultVo editDict(@RequestBody Complaint complaint) {
        return complaintService.updateComplaint(complaint);
    }

    @GetMapping("/list")
    public ResultVo listDict(Complaint complaint) {
        return complaintService.listComplaint(complaint);
    }
}
