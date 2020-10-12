package com.example.controller.system;

import com.example.anno.CheckPermissions;
import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.szOrder;
import com.example.service.szOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yangqianqian
 * @date: 2020/10/10
 * @description:
 */
@RestController
@RequestMapping("/order")
public class szOrderController {
    @Autowired
    private szOrderService szorderService;

    @SysLog("添加部门")
    @PostMapping("/add")
    @CheckPermissions("system:order:add")
    public ResultVo addDept(@RequestBody szOrder szorder) {
        return szorderService.addszOrder(szorder);
    }
}
