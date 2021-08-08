package com.study.spinrcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SentinelMain8041 {

    public static void main(String[] args) {
        SpringApplication.run(SentinelMain8041.class,args);
    }
}
