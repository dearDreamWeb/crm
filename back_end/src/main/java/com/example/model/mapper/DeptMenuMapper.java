package com.example.model.mapper;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author: pengjia
 * @date: 2020/9/27
 * @description:
 */
@Repository
public interface DeptMenuMapper {
    int addDeptMenu(@Param("deptId")Integer deptId,
                    @Param("menuId")Integer menuId);
    int delByDeptId(Integer deptId);
}
