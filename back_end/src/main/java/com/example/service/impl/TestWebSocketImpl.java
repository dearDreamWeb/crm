package com.example.service.impl;

import com.example.controller.WebSocketController;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: pengjia
 * @date: 2020/12/2
 * @description: 测试websocket向前端发送数据
 */
@Service
public class TestWebSocketImpl {
    @Autowired
    private WebSocketController socketController;

    public void testMessage() {
        Map map = new HashMap();
        map.put("data","这是测试websocket的map数据");
        map.put("offerId",1);
        Gson gson = new Gson();
        String json = gson.toJson(map);
        socketController.sendOneMessage("销售一号",json);
    }
}
