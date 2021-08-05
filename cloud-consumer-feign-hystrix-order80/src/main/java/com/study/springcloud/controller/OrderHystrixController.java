package com.study.springcloud.controller;


import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.study.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "gloabal_TimeOutHandler")
public class OrderHystrixController {

    @Resource
    PaymentHystrixService paymentHystrixService;
    @Value("${server.port}")
    String port;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String payment_Info_OK(@PathVariable("id")  Integer id){
        String result = paymentHystrixService.payment_Info_OK(id);
        return result;
    }
    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
  /*  @HystrixCommand(fallbackMethod = "payment_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })*/
    @HystrixCommand
    public String payment_Timeout(@PathVariable("id") Integer id){
        int age = 10/0;
        String result= paymentHystrixService.payment_Timeout(id);
        return result;
    }

    public String payment_TimeOutHandler(Integer id){
        return "我是消费者，请等待 id："+id;
    }

    public String gloabal_TimeOutHandler(){
        return  "全局处理： id";
    }




}
