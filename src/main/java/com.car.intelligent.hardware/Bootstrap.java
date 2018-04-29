package com.car.intelligent.hardware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by guobingwei on 18/4/28.
 */

@SpringBootApplication(scanBasePackages = "com.car.intelligent.hardware")
@ImportResource({"classpath:applicationContext.xml"})
public class Bootstrap {

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }
}
