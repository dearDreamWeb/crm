package com.example.controller.system;

import com.example.anno.CheckPermissions;
import com.example.anno.SysLog;
import com.example.entity.ResultVo;
import com.example.entity.request.ClueReq;
import com.example.entity.response.EmpResp;
import com.example.model.mapper.EmpMapper;
import com.example.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private EmpMapper empMapper;

    @SysLog("添加线索")
    @PostMapping("/add")
    @CheckPermissions("system:clue:add")
    public ResultVo addClue(@RequestBody ClueReq clueReq) {
        return clueService.addClue(clueReq);
    }

    @SysLog("删除线索")
    @PostMapping("/del")
    @CheckPermissions("system:clue:del")
    public ResultVo delClue(@RequestBody ClueReq clueReq,
                            HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        return clueService.delClue(clueReq,token);
    }

    @SysLog("编辑线索")
    @PostMapping("/edit")
    @CheckPermissions("system:clue:edit")
    public ResultVo editClue(@RequestBody ClueReq clueReq) {
        return clueService.editClue(clueReq);
    }

    @GetMapping
    public ResultVo getClue(@RequestParam("clueId")Integer clueId,
                            HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        return clueService.getClue(clueId,token);
    }

    @GetMapping("/detail")
    public ResultVo getClueDetail(@RequestParam("clueId")Integer clueId) {
        return clueService.getClueDetail(clueId);
    }

    @GetMapping("/list")
    public ResultVo listClue(ClueReq clueReq,HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        EmpResp empByToken = empMapper.getEmpByToken(token);
        return clueService.listClue(clueReq,empByToken.getEmpName());
    }

    @PostMapping("/front/add")
    public ResultVo frontAddClue(@RequestBody ClueReq clueReq) {
        return clueService.addClue(clueReq);
    }

    @PostMapping("/batch_edit_type")
    public ResultVo batchEditClueType(@RequestBody ClueReq clueReq) {
        return clueService.batchEditClueType(clueReq);
    }

    @GetMapping("/list_company")
    public ResultVo listCompany() {
        return clueService.listCompany();
    }

    @PostMapping("/transfer")
    public ResultVo transferCustomer(@RequestBody ClueReq clueReq,
                                     HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        return clueService.transferCustomer(clueReq,token);
    }
}
