package com.study.springcloud.service;


import com.study.springboot.entities.CommonResult;
import com.study.springboot.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallBackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {

        return new CommonResult<>(444,"处理异常。。。。");
    }
}
