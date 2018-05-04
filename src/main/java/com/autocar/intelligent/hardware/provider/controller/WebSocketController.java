package com.autocar.intelligent.hardware.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by guobingwei on 18/5/5.
 */
@ServerEndpoint("/autocar")
public class WebSocketController {

    private static int onlineCount = 0;
    private static Map<String, WebSocketController> clients = new ConcurrentHashMap<>();
    private Session session;
    private String username;

    @OnOpen
    public void onOpen() throws IOException {
        System.out.println("已连接");
    }

    @OnClose
    public void onClose() throws IOException {
        System.out.println("close");
    }

    @RequestMapping(value = "/upload")
    @OnMessage
    public void onMessage(String message) throws IOException {
        System.out.println("message");
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessageTo(String message, String To) throws IOException {
        // session.getBasicRemote().sendText(message);
        //session.getAsyncRemote().sendText(message);
        for (WebSocketController item : clients.values()) {
            if (item.username.equals(To))
                item.session.getAsyncRemote().sendText(message);
        }
    }

    public void sendMessageAll(String message) throws IOException {
        for (WebSocketController item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketController.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketController.onlineCount--;
    }

    public static synchronized Map<String, WebSocketController> getClients() {
        return clients;
    }
}
