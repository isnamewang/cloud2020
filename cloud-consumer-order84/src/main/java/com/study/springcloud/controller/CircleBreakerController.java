package com.study.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.study.springboot.entities.CommonResult;
import com.study.springboot.entities.Payment;
import com.study.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {
    public static final String SERVICE_URL = "http://cloud-payment-service";
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PaymentService paymentService;


    @GetMapping(value = "/consumer/fallback/{id}")
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "Blockhandler")
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL+"/paymentSQL/"+id,CommonResult.class,id);
        if(id == 4){
            throw new IllegalArgumentException("Illlllllll");
        }else if(result.getData() == null){
            throw new NullPointerException("nullllllll");
        }
        return result;
    }

    public CommonResult handlerFallback(@PathVariable("id") Long id, Throwable e){
        Payment payment = new Payment(id,null);
        return new CommonResult(444,"兜底Exception",payment);
    }


    public CommonResult Blockhandler(@PathVariable("id") Long id, BlockException e){
        Payment payment = new Payment(id,null);
        return new CommonResult(444,"BlockHandler",payment);
    }

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id")Long id){
        return paymentService.paymentSQL(id);
    }
}
