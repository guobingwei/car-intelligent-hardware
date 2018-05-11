package com.autocar.intelligent.hardware.provider.socket.tcp;

import com.alibaba.fastjson.JSON;
import com.autocar.intelligent.hardware.domain.param.CarDataUploadParam;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 客户端代码
 * Created by guobingwei on 2016/7/4.
 */
public class TCPClient {
    // 信道选择器
    private Selector selector;

    // 与服务器通信的信道
    SocketChannel socketChannel;

    // 要连接的服务器Ip地址
    private String hostIp;

    // 要连接的远程服务器在监听的端口
    private int hostListenningPort;

    /**
     * 构造函数
     * @param HostIp
     * @param HostListenningPort
     * @throws IOException
     */
    public TCPClient(String HostIp, int HostListenningPort) throws IOException{
        this.hostIp = HostIp;
        this.hostListenningPort = HostListenningPort;

        initialize();
    }

    /**
     * 初始化
     * @throws IOException
     */
    private void initialize() throws IOException {
        // 打开监听信道并设置为非阻塞模式
        socketChannel = SocketChannel.open(new InetSocketAddress(hostIp, hostListenningPort));
        socketChannel.configureBlocking(false);

        // 打开并注册选择器到信道
        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);

        // 启动读取线程
        new TCPClientReadThread(selector);
        System.out.println("init success");
    }

    /**
     * 发送字符串到服务器
     * @param message
     * @throws IOException
     */
    public void sendMsg(String message) throws IOException {
        ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes("UTF-8"));
        socketChannel.write(writeBuffer);
        System.out.println("sendMsg end");
    }

    public static void main(String[] args) throws IOException {
        TCPClient client = new TCPClient("127.0.0.1", 8900);

        CarDataUploadParam carDataUploadParam = new CarDataUploadParam();
        carDataUploadParam.setBackDistance(12.11);
        carDataUploadParam.setTemperature(23.5);
        client.sendMsg(JSON.toJSONString(carDataUploadParam));
    }
}
