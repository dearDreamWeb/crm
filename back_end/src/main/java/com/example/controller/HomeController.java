package com.example.controller;

import com.example.entity.ResultVo;
import com.example.entity.request.ClueReq;
import com.example.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: pengjia
 * @date: 2020/12/3
 * @description:
 */
@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private ClueService clueService;

    @GetMapping("/new_clue")
    public ResultVo newClue(ClueReq clueReq) {
        return clueService.newClueCount(clueReq);
    }
}
