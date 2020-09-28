package com.example.model.mapper;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author: pengjia
 * @date: 2020/9/27
 * @description:
 */
@Repository
public interface EmpDeptMapper {
    int addEmpDept(@Param("empId")Integer empId,
                   @Param("deptId")Integer deptId);
    int delByEmpId(Integer empId);
    int delByDeptId(Integer deptId);
}
