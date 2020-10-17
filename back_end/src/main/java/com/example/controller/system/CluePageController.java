package com.example.controller.system;

import com.example.entity.ResultVo;
import com.example.util.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: pengjia
 * @date: 2020/10/18
 * @description:
 */
@RestController
@RequestMapping("/clue_page")
public class CluePageController {
    @GetMapping
    public ResultVo testt(@RequestParam("activityId")Integer activityId,
                          @RequestParam("empId")Integer empId) {
        System.out.println(activityId+"----"+empId);
        String path = "/clue_page";
        return ResultUtils.response(path);
    }
}
