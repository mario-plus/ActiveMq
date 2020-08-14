package com.gree.mario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class MarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarioApplication.class, args);
    }

}
