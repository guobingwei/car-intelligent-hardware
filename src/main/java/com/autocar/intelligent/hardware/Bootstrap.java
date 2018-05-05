package com.autocar.intelligent.hardware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * Created by guobingwei on 18/4/28.
 */

@SpringBootApplication(scanBasePackages = "com.autocar.intelligent.hardware")
@ImportResource({"classpath:applicationContext.xml"})
@EnableWebSocket
public class Bootstrap {

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }
}
