package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.DeptReq;
import com.example.entity.response.DeptResp;
import com.example.entity.response.MenuResp;
import com.example.model.mapper.DeptMapper;
import com.example.model.mapper.DeptMenuMapper;
import com.example.model.mapper.EmpDeptMapper;
import com.example.service.DeptService;
import com.example.util.CheckUtils;
import com.example.util.DateUtils;
import com.example.util.MyListUtils;
import com.example.util.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/9/27
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private DeptMenuMapper deptMenuMapper;

    @Autowired
    private EmpDeptMapper empDeptMapper;

    @Override
    public ResultVo addDept(DeptReq deptReq) {
        CheckUtils.validate(deptReq);
        int checkDept = deptMapper.checkDept(deptReq.getDeptKey());
        if (checkDept != 0) {
            throw new SysException(ResultEnum.DEPT_EXIST.getCode(),
                    ResultEnum.DEPT_EXIST.getMessage());
        }
        int addDept = deptMapper.addDept(deptReq);
        if (addDept != 1) {
            throw new SysException(ResultEnum.DEPT_ADD_FAIL.getCode(),
                    ResultEnum.DEPT_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addDept);
    }

    @Override
    public ResultVo delDept(DeptReq deptReq) {
        Integer deptId = deptReq.getDeptId();
        DeptResp deptResp = deptMapper.getDept(deptId);
        if (deptResp == null) {
            throw new SysException(ResultEnum.DEPT_NOT_EXIST.getCode(),
                    ResultEnum.DEPT_NOT_EXIST.getMessage());
        }
        int delDept = deptMapper.delDept(deptId);
        if (delDept != 1) {
            throw new SysException(ResultEnum.DEPT_DEL_FAIL.getCode(),
                    ResultEnum.DEPT_DEL_FAIL.getMessage());
        }
        deptMenuMapper.delByDeptId(deptId);
        empDeptMapper.delByDeptId(deptId);
        return ResultUtils.response(delDept);
    }

    @Override
    public ResultVo editDept(DeptReq deptReq) {
        DeptResp deptResp = deptMapper.getDept(deptReq.getDeptId());
        if (deptResp == null) {
            throw new SysException(ResultEnum.DEPT_NOT_EXIST.getCode(),
                    ResultEnum.DEPT_NOT_EXIST.getMessage());
        }
        deptReq.setVersion(deptResp.getVersion());
        deptReq.setUpdateTime(DateUtils.getDate());
        int editDept = deptMapper.editDept(deptReq);
        if (editDept != 1) {
            throw new SysException(ResultEnum.DEPT_UPDATE_FAIL.getCode(),
                    ResultEnum.DEPT_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(editDept);
    }

    @Override
    public ResultVo getDept(Integer deptId) {
        DeptResp dept = deptMapper.getDept(deptId);
        List<MenuResp> menus = dept.getMenus();
        if (!MyListUtils.isEmpty(menus)) {
            List<Integer> list = new ArrayList<>();
            for (MenuResp menu : menus) {
                if (menu != null && menu.getMenuType() >= 3) {
                    list.add(menu.getMenuId());
                }
            }
            /* */
            dept.setMenuIds(list);
        }
        return ResultUtils.response(dept);
    }

    @Override
    public ResultVo listDept(DeptReq deptReq) {
        Integer pageNum = deptReq.getPageNum();
        Integer pageSize = deptReq.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<DeptResp> deptResps = deptMapper.listDept(deptReq);
        PageInfo<DeptResp> list = new PageInfo<>(deptResps);
        return ResultUtils.response(list);
    }

    /*@Override
    public ResultVo listDept(DeptReq deptReq) {
        List<DeptResp> deptResps = deptMapper.listDept(deptReq);
        return ResultUtils.response(deptResps);
    }*/

    @Override
    public ResultVo auth(DeptReq deptReq) {
        Integer deptId = deptReq.getDeptId();
        DeptResp deptResp = deptMapper.getDept(deptId);
        if (deptResp == null) {
            throw new SysException(ResultEnum.DEPT_NOT_EXIST.getCode(),
                    ResultEnum.DEPT_NOT_EXIST.getMessage());
        }
        List<Integer> menuIds = deptReq.getMenuIdList();
        if (menuIds != null && menuIds.size() > 0) {
            deptMenuMapper.delByDeptId(deptId);
            for (Integer menuId : menuIds) {
                int addDeptMenu = deptMenuMapper.addDeptMenu(deptId, menuId);
                if (addDeptMenu != 1) {
                    throw new SysException(ResultEnum.DEPT_AUTH_FAIL.getCode(),
                            ResultEnum.DEPT_AUTH_FAIL.getMessage());
                }
            }
        }
        return ResultUtils.response(1);
    }
}
