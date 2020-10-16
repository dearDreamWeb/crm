package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.Complaint;

/**
 * @author: liuleimin
 * @date: 2020/10/14
 * @description:
 */
public interface ComplaintService {
    ResultVo addComplaint(Complaint complaint);
    ResultVo updateComplaint(Complaint complaintReq);
    ResultVo deleteComplaint(Complaint complaintReq);
    ResultVo listComplaint(Complaint complaint);
    ResultVo getComplaint(Integer complaintId);
}
