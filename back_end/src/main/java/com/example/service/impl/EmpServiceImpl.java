package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.EmpReq;
import com.example.entity.response.DeptResp;
import com.example.entity.response.EmpResp;
import com.example.entity.response.MenuResp;
import com.example.model.mapper.EmpDeptMapper;
import com.example.model.mapper.EmpMapper;
import com.example.service.EmpService;
import com.example.util.CheckUtils;
import com.example.util.DateUtils;
import com.example.util.ResultUtils;
import com.example.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/9/27
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpDeptMapper empDeptMapper;

    @Override
    public ResultVo addEmp(EmpReq empReq) {
        CheckUtils.validate(empReq);
        int count = empMapper.checkEmp(empReq.getEmpName());
        if (count != 0) {
            throw new SysException(ResultEnum.USER_EXIST.getCode(),
                    ResultEnum.USER_EXIST.getMessage());
        }
        String passWord = empReq.getPassWord();
        //密码加密
        String passWordMd5 = DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(passWord.getBytes()).getBytes());
        empReq.setPassWord(passWordMd5);
        empReq.setEmpStatus(1);
        int addEmp = empMapper.addEmp(empReq);
        if (addEmp != 1){
            throw new SysException(ResultEnum.USER_ADD_FAIL.getCode(),
                    ResultEnum.USER_ADD_FAIL.getMessage());
        }
        Integer empId = empReq.getEmpId();
        List<Integer> deptIdList = empReq.getDeptIdList();
        if (deptIdList != null && deptIdList.size() > 0) {
            for (Integer deptId : deptIdList) {
                int addEmpDept = empDeptMapper.addEmpDept(empId, deptId);
                if (addEmpDept != 1){
                    throw new SysException(ResultEnum.USER_ADD_FAIL.getCode(),
                            ResultEnum.USER_ADD_FAIL.getMessage());
                }
            }
        }
        return ResultUtils.response(addEmp);
    }

    @Override
    public ResultVo delEmp(EmpReq empReq) {
        Integer empId = empReq.getEmpId();
        EmpResp emp = empMapper.getEmp(empId);
        if (emp == null) {
            throw new SysException(ResultEnum.USER_NOT_EXIST.getCode(),
                    ResultEnum.USER_NOT_EXIST.getMessage());
        }
        int delEmp = empMapper.delEmp(empId);
        if (delEmp != 1) {
            throw new SysException(ResultEnum.USER_DEL_FAIL.getCode(),
                    ResultEnum.USER_DEL_FAIL.getMessage());
        }
        empDeptMapper.delByEmpId(empId);
        return ResultUtils.response(delEmp);
    }

    @Override
    public ResultVo editEmp(EmpReq empReq) {
        EmpResp emp = empMapper.getEmp(empReq.getEmpId());
        if (emp == null) {
            throw new SysException(ResultEnum.USER_NOT_EXIST.getCode(),
                    ResultEnum.USER_NOT_EXIST.getMessage());
        }
        int editEmp = empMapper.editEmp(empReq);
        if (editEmp != 1) {
            throw new SysException(ResultEnum.USER_UPDATE_FAIL.getCode(),
                    ResultEnum.USER_UPDATE_FAIL.getMessage());
        }
        List<Integer> deptIdList = empReq.getDeptIdList();
        if (deptIdList != null && deptIdList.size() > 0) {
            Integer empId = empReq.getEmpId();
            empMapper.delEmp(empId);
            for (Integer deptId : deptIdList) {
                int addEmpDept = empDeptMapper.addEmpDept(empId, deptId);
                if (addEmpDept != 1) {
                    throw new SysException(ResultEnum.USER_UPDATE_FAIL.getCode(),
                            ResultEnum.USER_UPDATE_FAIL.getMessage());
                }
            }
        }
        return ResultUtils.response(editEmp);
    }

    @Override
    public ResultVo getEmp(Integer empId) {
        EmpResp emp = empMapper.getEmp(empId);
        return ResultUtils.response(emp);
    }

    @Override
    public ResultVo listEmp(EmpReq empReq) {
        return null;
    }

    @Override
    public ResultVo login(EmpReq empReq) {
        String empName = empReq.getEmpName();
        EmpResp empResp = empMapper.login(empName);
        if (empResp == null) {
            throw new SysException(ResultEnum.USER_OR_PASSWORD_ERROR.getCode(),
                    ResultEnum.USER_OR_PASSWORD_ERROR.getMessage());
        }
        Integer empStatus = empResp.getEmpStatus();
        if (empStatus == null || empStatus == 0) {
            throw new SysException(ResultEnum.ACCOUNT_NOT_ACTIVATE.getCode(),
                    ResultEnum.ACCOUNT_NOT_ACTIVATE.getMessage());
        }
        String passWordDb = empResp.getPassWord();
        String passWord = empReq.getPassWord();
        String passWordMd5 = DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(passWord.getBytes()).getBytes());
        if (!passWordDb.equals(passWordMd5)) {
            throw new SysException(ResultEnum.USER_OR_PASSWORD_ERROR.getCode(),
                    ResultEnum.USER_OR_PASSWORD_ERROR.getMessage());
        }
        Date date = DateUtils.getDate();
        long time = date.getTime();
        String timeStamp = empResp.getEmpName()+empResp.getEmpId()+time;
        String token = DigestUtils.md5DigestAsHex(timeStamp.getBytes());
        empMapper.updateToken(empName,token);
        return ResultUtils.response(empResp);
    }

    @Override
    public ResultVo getShowMenuByToken(String token) {
        List<MenuResp> menus = empMapper.getShowMenuByToken(token);
        //权限封装成树形结构
        List<MenuResp> menuResps = TreeUtils.listToMenuTree(menus);
        return ResultUtils.response(menuResps);
    }

    @Override
    public ResultVo pwd(EmpReq empReq) {
        return null;
    }

    @Override
    public ResultVo logout(EmpReq empReq) {
        return null;
    }
}
