package com.study.springcloud.service;


import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements  PaymentHystrixService{


    @Override
    public String payment_Info_OK(Integer id) {
        return "backfall ok";
    }

    @Override
    public String payment_Timeout(Integer id) {
        return "backfall timeout";
    }
}
