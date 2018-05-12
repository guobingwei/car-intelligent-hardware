package com.autocar.intelligent.hardware.provider.socket.tcp.test;

import com.alibaba.fastjson.JSON;
import com.autocar.intelligent.hardware.domain.param.CarDataUploadParam;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by guobingwei on 18/5/11.
 */
public class SocketClient {


    public static void main(String[] args) {
        try {
            //1.建立客户端socket连接，指定服务器位置及端口
            Socket socket = new Socket("localhost", 8900);
            //2.得到socket读写流
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            //输入流
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            //3.利用流按照一定的操作，对socket进行读写操作
            CarDataUploadParam carDataUploadParam = new CarDataUploadParam();
            carDataUploadParam.setBackDistance(111.11);
            carDataUploadParam.setTemperature(213.5);

            pw.write(JSON.toJSONString(carDataUploadParam));
            pw.flush();
            socket.shutdownOutput();
            //接收服务器的相应
            String reply = null;
            while (!((reply = br.readLine()) == null)) {
                System.out.println("接收服务器的信息：" + reply);
            }
            //4.关闭资源
            br.close();
            is.close();
            pw.close();
            os.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
