package com.study.springcloud.service;


import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     * 正常访问
     * @param id
     * @return
     */
    public String paymentInfo_ok(Integer id){
        return "线程池： "+Thread.currentThread().getName()+" payment_ok,id: "+id+"  haha";
    }

    @HystrixCommand(fallbackMethod = "payment_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String payment_TimeOut(Integer id){
        int timenumber =5;
        try{
            TimeUnit.SECONDS.sleep(timenumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： "+Thread.currentThread().getName()+" payment_TimeOut,id: "+id+"  耗时秒"+timenumber;
    }

    public String payment_TimeOutHandler(Integer id){
        return "线程池： "+Thread.currentThread().getName()+" payment_TimeOutHandler,id: "+id;
    }
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerHandler",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),

    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id<0){
            throw  new RuntimeException("id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"流水号："+serialNumber;
    }

    public String paymentCircuitBreakerHandler(Integer id){
        return "id 不能为负数";
    }

}
