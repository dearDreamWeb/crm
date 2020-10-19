package com.example.model.mapper;

import com.example.entity.request.Complaint;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: liuleimin
 * @date: 2020/10/14
 * @description:
 */
@Repository
public interface ComplaintMapper {
    List<Complaint> listComplaint(Complaint complaint);
    Complaint getComplaint(Integer complaintId);
    int addComplaint(Complaint complaint);
    int updateComplaint(Complaint complaint);
    int deleteComplaint(Integer complaintId);
}
