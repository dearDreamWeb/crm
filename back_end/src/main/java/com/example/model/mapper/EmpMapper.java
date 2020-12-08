package com.example.model.mapper;

import com.example.entity.request.EmpReq;
import com.example.entity.response.EmpResp;
import com.example.entity.response.MenuResp;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/9/26
 * @description:
 */
@Repository
public interface EmpMapper {
    int addEmp(EmpReq empReq);
    int delEmp(Integer empId);
    int editEmp(EmpReq empReq);
    EmpResp getEmp(Integer empId);
    List<EmpResp> listEmp(EmpReq empReq);
    int checkEmp(String empName);
    List<MenuResp> getShowMenuByToken(String token);
    List<MenuResp> findMenuByToken(String token);
    EmpResp login(String empName);
    int updateToken(@Param("empName")String empName,
                    @Param("token")String token);
    EmpResp getEmpByToken(String token);
    EmpResp getMenuByEmpId(Integer empId);
    int updatePassWord(@Param("empName")String empName,
                       @Param("newPassWordMd5")String newPassWordMd5);
    List<EmpResp> listNotAuthActivity(Integer activityId);
    EmpResp getEmpByPersonal(Integer empId);
}
