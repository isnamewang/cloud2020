package com.study.springcloud;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.xml.soap.SAAJResult;

@SpringBootApplication
@Slf4j
@EnableFeignClients
public class OrderFeMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderFeMain80.class, args);
    }
}
