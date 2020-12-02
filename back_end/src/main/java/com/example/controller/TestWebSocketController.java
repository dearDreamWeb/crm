package com.example.controller;

import com.example.service.impl.TestWebSocketImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: pengjia
 * @date: 2020/12/2
 * @description:
 */
@RestController
@RequestMapping("/socket")
public class TestWebSocketController {
    @Autowired
    private TestWebSocketImpl testWebSocket;

    @GetMapping("/test")
    public void testSocket() {
        testWebSocket.testMessage();
    }
}
