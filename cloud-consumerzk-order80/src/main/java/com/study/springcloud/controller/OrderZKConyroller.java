package com.study.springcloud.controller;


import com.study.springboot.entities.CommonResult;
import com.study.springboot.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderZKConyroller {

    public static final String PAYMENT_URL="http://cloud-provider-payment";

    @Resource
    RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    public String zk(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/zk",String.class);
    }





}
