package com.study.springcloud.controller;


import com.study.springboot.entities.CommonResult;
import com.study.springboot.entities.Payment;
import com.study.springcloud.service.PaymentFeginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeginController {

    @Resource
    private PaymentFeginService service;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        log.info("进来-----------------0----------");
        CommonResult forObject = service.getPaymentById(id);
        log.info(forObject.toString()+"--------------1-------------------");
        return forObject;
    }
}
