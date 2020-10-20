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

    @SysLog("添加")
    @PostMapping("/add")
    public ResultVo addDict(@RequestBody Complaint complaint) {
        return complaintService.addComplaint(complaint);
    }

    @SysLog("删除")
    @PostMapping("/del")
    public ResultVo delDict(@RequestBody Complaint complaint) {
        return complaintService.deleteComplaint(complaint.getComplaintId());
    }
    @GetMapping
    public ResultVo getDict(@RequestParam("complaintId")Integer complaintId) {
        return complaintService.getComplaint(complaintId);
    }

    @SysLog("修改")
    @PostMapping("/update")
    public ResultVo editDict(@RequestBody Complaint complaint) {
        System.out.println("待膝盖的数据是："+complaint);
        return complaintService.updateComplaint(complaint);
    }

    @GetMapping("/list")
    public ResultVo listDict(Complaint complaint) {
        return complaintService.listComplaint(complaint);
    }
}
