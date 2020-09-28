package com.example.controller.system;

import com.example.entity.ResultVo;
import com.example.entity.request.MenuReq;
import com.example.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: pengjia
 * @date: 2020/9/27
 * @description:
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PostMapping("/add")
    public ResultVo addMenu(MenuReq menuReq) {
        return menuService.addMenu(menuReq);
    }

    @PostMapping("/del")
    public ResultVo delMenu(MenuReq menuReq) {
        return menuService.delMenu(menuReq);
    }

    @PostMapping("/edit")
    public ResultVo editMenu(MenuReq menuReq) {
        return menuService.editMenu(menuReq);
    }

    @GetMapping
    public ResultVo getMenu(Integer menuId) {
        return menuService.getMenu(menuId);
    }

    @GetMapping("/list")
    public ResultVo listMenu(MenuReq menuReq) {
        return menuService.listMenu(menuReq);
    }

    @GetMapping("/tree")
    public ResultVo treeMenu(MenuReq menuReq) {
        return menuService.treeMenu(menuReq);
    }
}
