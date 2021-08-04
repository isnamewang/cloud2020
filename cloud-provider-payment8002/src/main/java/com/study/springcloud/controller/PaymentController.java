package com.study.springcloud.controller;


import com.study.springboot.entities.CommonResult;
import com.study.springboot.entities.Payment;
import com.study.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*****插入结果****: "+result);
        if(result>0){
            return new CommonResult(200,"插入成功,port: "+port,result);
        }else{
            return new CommonResult(444,"插入失败",null);
        }

    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment result = paymentService.getPaymentById(id);
        log.info("*****得到结果****: "+result);
        if(result!=null){
            return new CommonResult(200,"查询成功,port: "+port,result);
        }else{
            return new CommonResult(444,"查询失败,查询ID： "+id,null);
        }

    }


}
