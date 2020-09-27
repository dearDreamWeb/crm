package com.example.service;

import com.example.entity.ResultVo;
import com.example.entity.request.MenuReq;

/**
 * @author: pengjia
 * @date: 2020/9/27
 * @description:
 */
public interface MenuService {
    ResultVo addMenu(MenuReq menuReq);
    ResultVo delMenu(MenuReq menuReq);
    ResultVo editMenu(MenuReq menuReq);
    ResultVo getMenu(Integer menuId);
    ResultVo listMenu(MenuReq menuReq);
    ResultVo treeMenu(MenuReq menuReq);
}
