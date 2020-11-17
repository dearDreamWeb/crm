package com.example.controller.system;

import com.example.anno.SysLog;
import com.example.entity.Grading;
import com.example.entity.Qualitative;
import com.example.entity.Ration;
import com.example.entity.ResultVo;
import com.example.service.SanYiKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: pengjia
 * @date: 2020/11/16
 * @description:
 */
@RestController
@RequestMapping("/san_yi")
public class SanYiKeController {
    @Autowired
    private SanYiKeService sanYiKeService;

    @SysLog("客户定性")
    @PostMapping("/ration")
    public ResultVo addRation(@RequestBody Ration ration,
                              HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        return sanYiKeService.addRation(ration,token);
    }

    @SysLog("客户定级")
    @PostMapping("/grading")
    public ResultVo addGrading(@RequestBody Grading grading,
                               HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        return sanYiKeService.addGrading(grading,token);
    }

    @SysLog("客户定量")
    @PostMapping("/qualitative")
    public ResultVo addQualitative(@RequestBody Qualitative qualitative,
                                   HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        return sanYiKeService.addQualitative(qualitative,token);
    }
}
