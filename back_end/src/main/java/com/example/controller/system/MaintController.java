package com.example.controller.system;

import com.example.entity.ResultVo;
import com.example.entity.request.Maint;
import com.example.service.MaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: liuleimin
 * @date: 2020/11/16
 * @description:
 */
@RestController
@RequestMapping("/maint")
public class MaintController {
    @Autowired
    private MaintService maintService;

    @GetMapping("/list")
    public ResultVo listDict(Maint maint) {
        return maintService.listMaint(maint);
    }
}
