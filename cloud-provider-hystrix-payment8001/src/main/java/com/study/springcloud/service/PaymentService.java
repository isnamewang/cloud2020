package com.study.springcloud.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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

}
