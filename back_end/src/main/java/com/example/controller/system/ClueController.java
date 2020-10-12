package com.example.controller.system;

import com.example.anno.CheckPermissions;
import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.ClueReq;
import com.example.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: pengjia
 * @date: 2020/10/12
 * @description:
 */
@RestController
@RequestMapping("/clue")
public class ClueController {
    @Autowired
    private ClueService clueService;

    @SysLog("添加线索")
    @PostMapping("/add")
    @CheckPermissions("system:clue:add")
    public ResultVo addClue(@RequestBody ClueReq clueReq) {
        return clueService.addClue(clueReq);
    }

    @SysLog("删除线索")
    @PostMapping("/del")
    @CheckPermissions("system:clue:del")
    public ResultVo delClue(@RequestBody ClueReq clueReq) {
        return clueService.delClue(clueReq);
    }

    @SysLog("编辑线索")
    @PostMapping("/edit")
    @CheckPermissions("system:clue:edit")
    public ResultVo editClue(@RequestBody ClueReq clueReq) {
        return clueService.editClue(clueReq);
    }

    @GetMapping
    public ResultVo getClue(@RequestParam("clueId")Integer clueId) {
        return clueService.getClue(clueId);
    }

    @GetMapping("/list")
    public ResultVo listClue(@RequestBody ClueReq clueReq) {
        return clueService.listClue(clueReq);
    }
}
