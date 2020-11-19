package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.SolutionReq;
import com.example.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: pengjia
 * @date: 2020/11/2
 * @description:
 */
@RestController
@RequestMapping("/solution")
public class SolutionController {
    @Autowired
    private SolutionService solutionService;

    @SysLog("添加方案")
    @PostMapping("/add")
    public ResultVo addSolution(@RequestBody SolutionReq solutionReq) {
        return solutionService.addSolution(solutionReq);
    }

    @SysLog("删除方案")
    @PostMapping("/del")
    public ResultVo delSolution(@RequestBody SolutionReq solutionReq) {
        return solutionService.delSolution(solutionReq);
    }

    @SysLog("修改方案")
    @PostMapping("/edit")
    public ResultVo editSolution(@RequestBody SolutionReq solutionReq) {
        return solutionService.editSolution(solutionReq);
    }

    @GetMapping
    public ResultVo getSolution(SolutionReq solutionReq) {
        return solutionService.getSolution(solutionReq);
    }

    @GetMapping("/list")
    public ResultVo listSolution(SolutionReq solutionReq) {
        return solutionService.listSolution(solutionReq);
    }

    @GetMapping("/list_by_cus")
    public ResultVo listByCus(SolutionReq solutionReq) {
        return solutionService.listByCus(solutionReq);
    }
}
