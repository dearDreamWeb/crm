package com.example.service.impl;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: pengjia
 * @date: 2020/10/16
 * @description:
 */
@Component
@ServerEndpoint("/socket")
public class WebSocketService {
    /**
     * 全部在线会话
     */
    private static Map<String,Session> onlineSessions = new ConcurrentHashMap<>();

    /**
     * 当客户端打开连接：1.添加会话对象 2.更新在线人数
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        onlineSessions.put(session.getId(),session);
        System.out.println(session.getId());
    }

    /**
     * 当客户端发送消息：1.获取它的用户名和消息 2.发送消息给所有人
     * 约定传递的消息为JSON字符串，方便传递更多参数
     * @param session
     * @param jsonStr
     */
    @OnMessage
    public void onMessage(Session session,String jsonStr) {

    }

    /**
     * 当关闭连接：1.移除会话对象 2.更新在线人数
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        onlineSessions.remove(session.getId());
    }

    /**
     * 当通信发生异常：打印错误日志
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session,Throwable error) {
        error.printStackTrace();
    }

    /**
     * 公共方法：发送消息给所有人
     * @param jsonMsg
     */
    public void sendMessageToAll(String jsonMsg) {
        onlineSessions.forEach((id,session) -> {
            try {
                session.getBasicRemote().sendText(jsonMsg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 公共方法：发送消息给指定人
     * @param jsonStr
     */
    public void sendMessageToSingle(String jsonStr) {
        Session session = onlineSessions.get(1);
        try {
            session.getBasicRemote().sendText(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
