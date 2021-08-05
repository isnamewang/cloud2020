package com.study.springcloud.service;

import com.study.springboot.entities.CommonResult;
import com.study.springboot.entities.Payment;
import com.study.springcloud.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-payment-service",configuration = FeignConfig.class)
public interface PaymentFeginService {

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
