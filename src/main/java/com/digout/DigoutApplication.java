package com.digout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DigoutApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigoutApplication.class, args);
    }
}
