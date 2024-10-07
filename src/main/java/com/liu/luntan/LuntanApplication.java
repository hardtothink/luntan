package com.liu.luntan;

import jakarta.annotation.PostConstruct;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//@MapperScan(basePackages = {"com.liu.luntan.dao"})//wakao
@ComponentScan(basePackages = {"com.liu.luntan.config","com.liu.luntan.controller.interceptor","com.liu"})
@SpringBootApplication
public class LuntanApplication {

    @PostConstruct
    public void init() {
        // 解决netty启动冲突问题
        // see Netty4Utils.setAvailableProcessors()
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext contex = SpringApplication.run(LuntanApplication.class, args);
        System.out.println(contex);
    }

}
