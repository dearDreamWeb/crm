package com.example.model.mapper;

import com.example.entity.request.DeptReq;
import com.example.entity.response.DeptResp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/9/27
 * @description:
 */
@Repository
public interface DeptMapper {
    int addDept(DeptReq deptReq);
    int delDept(Integer deptId);
    int editDept(DeptReq deptReq);
    DeptResp getDept(Integer deptId);
    List<DeptResp> listDept();
    int checkDept(String deptKey);
}
