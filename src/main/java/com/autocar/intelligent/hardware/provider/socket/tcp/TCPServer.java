package com.autocar.intelligent.hardware.provider.socket.tcp;

import com.alibaba.fastjson.JSON;
import com.autocar.intelligent.hardware.domain.model.CarDataUploadModel;
import com.autocar.intelligent.hardware.provider.socket.UploadSocketService;
import com.autocar.intelligent.hardware.service.websocket.WebSocketService;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务端
 * Created by guobingwei on 2016/7/4.
 */
@Service
public class TCPServer {

    private static Logger logger = LoggerFactory.getLogger(TCPServer.class);

    // 缓冲区大小
    private static final int BUFFER_SIZE = 1024;

    // 超时时间，单位毫秒
    private static final int TIME_OUT = 3000;

    // 本地监听端口
    private static final int LISTEN_PORT = 8900;

    private static ExecutorService executorService = Executors.newFixedThreadPool(100);


    @Resource
    private UploadSocketService uploadSocketService;


//    static {
//        executorService.submit(() -> {
//            try {
//                init();
//            } catch (IOException e) {
//                logger.error("socket server 初始化失败 error={}", e.getMessage(), e);
//            }
//        });
//    }

    /***
     * TCPServer初始化逻辑
     * @throws IOException
     */

    private static void init() throws IOException {
        // 创建选择器
        Selector selector = Selector.open();

        // 打开监听信道
        ServerSocketChannel listenerChannel = ServerSocketChannel.open();

        // 与本地端口绑定
        listenerChannel.socket().bind(new InetSocketAddress(LISTEN_PORT));

        // 设置为非阻塞模式
        listenerChannel.configureBlocking(false);

        // 将选择器绑定到监听信道,只有非阻塞信道才可以注册选择器.并在注册过程中指出该信道可以进行Accept操作
        listenerChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 创建一个处理协议的实现类,由它来具体操作
        TCPProtocol protocol = new TCPProtocolImpl(BUFFER_SIZE);

        // 反复循环,等待IO
        while (true) {
            // 等待某信道就绪(或超时)
            if (selector.select(TIME_OUT) == 0) {
                logger.info("等待通信信道就绪......");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    logger.error("error={}", e.getMessage(), e);
                }
                continue;
            }

            // 取得迭代器.selectedKeys()中包含了每个准备好某一I/O操作的信道的SelectionKey
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();

            while (keyIter.hasNext()) {
                SelectionKey key = keyIter.next();

                try {
                    if (key.isAcceptable()) {
                        // 有客户端连接请求时
                        protocol.handleAccept(key);
                    }

                    if (key.isReadable()) {
                        // 从客户端读取数据
                        protocol.handleRead(key);
                    }

                    if (key.isValid() && key.isWritable()) {
                        // 客户端可写时
                        protocol.handleWrite(key);
                    }
                } catch (IOException ex) {
                    // 出现IO异常（如客户端断开连接）时移除处理过的键
                    keyIter.remove();
                    continue;
                }

                // 移除处理过的键
                keyIter.remove();
            }
        }
    }

    @PostConstruct
    public void init2() {
        executorService.submit( () -> {
//            initSocketServer();
            logger.info("异步线程");
            // 随机刷新页面数据
            randomShow();
        });
    }

    private void initSocketServer() {
        try {
            logger.info("socket init....");
            //1.建立一个服务器Socket(ServerSocket)绑定指定端口
            ServerSocket serverSocket = new ServerSocket(8900);

            while (true) {
                //2.使用accept()方法阻止等待监听，获得新连接
                Socket socket = serverSocket.accept();
                logger.info("等待监听....");

                //3.获得输入流
                InputStream is = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                logger.info("获得输入流 br={}", br);

                //获得输出流
                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os);

                //4.读取用户输入信息
                String info = null;
                while (!((info = br.readLine()) == null)) {
                    logger.info("我是服务器，接收到的信息为：" + info);
                    uploadSocketService.handleReceive(info);
                }

                //给客户一个响应
                String reply = "welcome";
                pw.write(reply);
                pw.flush();

                socket.close();
                is.close();
                logger.info("资源关闭成功");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    logger.error("线程sleep异常", e);
                }
            }

        } catch (IOException e) {
            logger.error("socket 初始化异常", e);
        }
    }


    private void randomShow() {
        double distanceRandomNum [] = {42.5, 67.4, 24.2, 56.1, 19.4};
        double temperatureRandomNum [] = {21.3, 21.3, 21.3, 21.4, 21.4};
        while (true) {

            for (int i = 0; i < distanceRandomNum.length; i++) {
                double distance = distanceRandomNum[i];
                double temperature = temperatureRandomNum[i];
                CarDataUploadModel carDataUploadModel = new CarDataUploadModel();
                carDataUploadModel.setBackDistance(distance);
                carDataUploadModel.setTemperature(temperature);
                WebSocketService.broadcastMessage(JSON.toJSONString(carDataUploadModel));
                logger.info("随机刷新数据 data={}", carDataUploadModel);
                try {
                    Thread.sleep(1000 * 2);
                } catch (InterruptedException e) {
                    logger.error("随机刷新页面数据 异常");
                    break;
                }
            }
        }
    }
}
