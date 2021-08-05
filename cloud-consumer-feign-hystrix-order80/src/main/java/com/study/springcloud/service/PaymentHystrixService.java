package com.study.springcloud.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String payment_Info_OK(@PathVariable("id")  Integer id);

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String payment_Timeout(@PathVariable("id") Integer id);

}
