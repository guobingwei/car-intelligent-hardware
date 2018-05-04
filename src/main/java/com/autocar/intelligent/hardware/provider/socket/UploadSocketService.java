package com.autocar.intelligent.hardware.provider.socket;

/**
 * Created by guobingwei on 18/4/29.
 */
public interface UploadSocketService {

    /***
     * 处理消息接收逻辑
     * @param JSONString
     */
    Object handleReceive(String JSONString);
}
