package com.example.service.impl;

import com.example.common.enums.ResultEnum;
import com.example.common.exception.SysException;
import com.example.entity.ResultVo;
import com.example.entity.request.MenuReq;
import com.example.entity.response.MenuResp;
import com.example.model.mapper.MenuMapper;
import com.example.service.MenuService;
import com.example.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: pengjia
 * @date: 2020/9/27
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public ResultVo addMenu(MenuReq menuReq) {
        CheckUtils.validate(menuReq);
        String pids = MyStringUtils.listToString(menuReq.getPidList());
        menuReq.setPids(pids);
        Integer pid = menuReq.getPid();
        if (pid == null) {
            pid = 0;
        }
        Integer type = menuReq.getMenuType();
        if (type == null) {
            menuReq.setMenuType(1);
        }
        menuReq.setPid(pid);
        int addMenu = menuMapper.addMenu(menuReq);
        if (addMenu != 1) {
            throw new SysException(ResultEnum.MENU_ADD_FAIL.getCode(),
                    ResultEnum.MENU_ADD_FAIL.getMessage());
        }
        return ResultUtils.response(addMenu);
    }

    @Override
    public ResultVo delMenu(MenuReq menuReq) {
        Integer menuId = menuReq.getMenuId();
        MenuResp menuResp = menuMapper.getMenu(menuId);
        if (menuResp == null) {
            throw new SysException(ResultEnum.MENU_NOT_EXIST.getCode(),
                    ResultEnum.MENU_NOT_EXIST.getMessage());
        }
        int delMenu = menuMapper.delMenu(menuId);
        if (delMenu != 1) {
            throw new SysException(ResultEnum.MENU_DEL_FAIL.getCode(),
                    ResultEnum.MENU_DEL_FAIL.getMessage());
        }
        return ResultUtils.response(delMenu);
    }

    @Override
    public ResultVo editMenu(MenuReq menuReq) {
        Integer menuId = menuReq.getMenuId();
        MenuResp menuResp = menuMapper.getMenu(menuId);
        if (menuResp == null) {
            throw new SysException(ResultEnum.MENU_NOT_EXIST.getCode(),
                    ResultEnum.MENU_NOT_EXIST.getMessage());
        }
        Integer pid = menuReq.getPid();
        if (menuId.equals(pid)) {
            throw new SysException(ResultEnum.MENU_PID_NOT_SELF.getCode(),
                    ResultEnum.MENU_PID_NOT_SELF.getMessage());
        }
        String pids = MyStringUtils.listToString(menuReq.getPidList());
        menuReq.setPids(pids);
        int editMenu = menuMapper.editMenu(menuReq);
        if (editMenu != 1) {
            throw new SysException(ResultEnum.MENU_UPDATE_FAIL.getCode(),
                    ResultEnum.MENU_UPDATE_FAIL.getMessage());
        }
        return ResultUtils.response(editMenu);
    }

    @Override
    public ResultVo getMenu(Integer menuId) {
        MenuResp menu = menuMapper.getMenu(menuId);
        String pids = menu.getPids();
        List<Integer> list = MyStringUtils.stringToList(pids);
        menu.setPidList(list);
        return ResultUtils.response(menu);
    }

    @Override
    public ResultVo listMenu(MenuReq menuReq) {
        return null;
    }

    @Override
    public ResultVo treeMenu(MenuReq menuReq) {
        List<MenuResp> menuResps = menuMapper.listMenu(menuReq);
        menuResps = stringToList(menuResps);
        List<MenuResp> menuRespList = TreeUtils.listToMenuTree(menuResps);
        return ResultUtils.response(menuRespList);
    }

    private List<MenuResp> stringToList(List<MenuResp> menuResps) {
        if (!MyListUtils.isEmpty(menuResps)) {
            for (MenuResp menuResp : menuResps) {
                String pids = menuResp.getPids();
                List<Integer> list = MyStringUtils.stringToList(pids);
                menuResp.setPidList(list);
            }
        }
        return menuResps;
    }
}
