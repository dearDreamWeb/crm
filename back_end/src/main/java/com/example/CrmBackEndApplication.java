package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@MapperScan("com.example.model.mapper")
@SpringBootApplication
@EnableWebSocket
public class CrmBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmBackEndApplication.class, args);
    }

}
