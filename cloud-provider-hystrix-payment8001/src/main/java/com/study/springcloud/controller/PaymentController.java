package com.study.springcloud.controller;

import com.study.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    String port;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String payment_Info_OK(@PathVariable("id")  Integer id){
        String result = paymentService.paymentInfo_ok(id);
        log.info("*****result: "+result);
        return result;
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String payment_Timeout(@PathVariable("id") Integer id){
        String result= paymentService.payment_TimeOut(id);
        log.info("*****timeout result: "+result);
        return result;
    }
}
