package com.example.controller;

import com.example.entity.ResultVo;
import com.example.service.WebSocket;
import com.example.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: pengjia
 * @date: 2020/10/16
 * @description:
 */
@RestController
public class WebSocketController {
    @Autowired
    private WebSocket webSocket;

    @GetMapping("/sendAllWebSocket")
    public String test() {
        String text="你们好！这是websocket群体发送！";
        webSocket.sendAllMessage(text);
        return text;
    }

    @GetMapping("/sebdOneWebSocket/{userName}")
    public String sendOneWebSocket(@RequestParam("empName")String userName) {
        String text=userName+" 你好！ 这是websocket单人发送！";
        webSocket.sendOneMessage(userName,text);
        return text;
    }
}
