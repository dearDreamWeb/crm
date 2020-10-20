package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.Qa;
import com.example.service.QaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: liuleimin
 * @date: 2020/10/20
 * @description:
 */
@RestController
@RequestMapping("/qa")
public class QaController {
    @Autowired
    private QaService qaService;

    @SysLog("添加")
    @PostMapping("/add")
    public ResultVo addDict(@RequestBody Qa qa) {
        return qaService.addQa(qa);
    }

    @SysLog("删除")
    @PostMapping("/del")
    public ResultVo delDict(@RequestBody Qa qa) {
        System.out.println("获取的参数是："+qa);
        return qaService.deleteQa(qa.getQaId());
    }

    @SysLog("修改")
    @PostMapping("/update")
    public ResultVo editDict(@RequestBody Qa qa) {

        System.out.println("待膝盖的数据是："+qa);
        return qaService.updateQa(qa);
    }

    @GetMapping
    public ResultVo getDict(@RequestParam("qaId")Integer qaId) {
        return qaService.getQa(qaId);
    }

    @GetMapping("/list")
    public ResultVo listDict(Qa qa) {
        return qaService.listQa(qa);
    }

}
