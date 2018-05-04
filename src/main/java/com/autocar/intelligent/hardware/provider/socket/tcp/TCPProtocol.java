package com.autocar.intelligent.hardware.provider.socket.tcp;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * TCPServerSelector与特定协议间通信的接口
 * Created by guobingwei on 2016/7/4.
 */
public interface TCPProtocol {
    /**
     * 接收一个SocketChannel的处理
     * @param key
     * @throws IOException
     */
    void handleAccept(SelectionKey key) throws IOException;

    /**
     * 从一个SocketChannel读取信息的处理
     * @param key
     * @throws IOException
     */
    void handleRead(SelectionKey key) throws IOException;

    /**
     * 向一个SocketChannel写入信息的处理
     * @param key
     * @throws IOException
     */
    void handleWrite(SelectionKey key) throws IOException;
}
