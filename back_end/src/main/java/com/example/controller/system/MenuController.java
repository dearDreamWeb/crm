package com.example.controller.system;

import com.example.anno.CheckPermissions;
import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.MenuReq;
import com.example.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @SysLog("添加菜单")
    @PostMapping("/add")
    @CheckPermissions("system:menu:add")
    public ResultVo addMenu(@RequestBody MenuReq menuReq) {
        return menuService.addMenu(menuReq);
    }

    @SysLog("删除菜单")
    @PostMapping("/del")
    @CheckPermissions("system:menu:del")
    public ResultVo delMenu(@RequestBody MenuReq menuReq) {
        return menuService.delMenu(menuReq);
    }

    @SysLog("编辑菜单")
    @PostMapping("/edit")
    @CheckPermissions("system:menu:edit")
    public ResultVo editMenu(@RequestBody MenuReq menuReq) {
        return menuService.editMenu(menuReq);
    }

    @GetMapping
    public ResultVo getMenu(Integer menuId) {
        return menuService.getMenu(menuId);
    }

    @GetMapping("/list")
    @CheckPermissions("system:menu:index")
    public ResultVo listMenu(MenuReq menuReq) {
        return menuService.listMenu(menuReq);
    }

    @GetMapping("/tree")
    @CheckPermissions("system:menu:index")
    public ResultVo treeMenu(MenuReq menuReq) {
        return menuService.treeMenu(menuReq);
    }
}
