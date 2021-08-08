package com.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class paymentMain9004 {
    public static void main(String[] args) {
        SpringApplication.run(paymentMain9004.class,args);
    }
}