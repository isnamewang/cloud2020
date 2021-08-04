package com.study.springcloud.controller;


import com.study.springboot.entities.CommonResult;
import com.study.springboot.entities.Payment;
import com.study.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.xml.ws.Response;
import java.net.URI;
import java.net.URL;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Resource
    RestTemplate restTemplate;

    @Resource
    LoadBalancer loadBalancer;
    @Resource
    DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        log.info("进来-----------------0----------");
        CommonResult forObject = restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        log.info(forObject.toString()+"--------------1-------------------");
        return forObject;
    }


    @GetMapping("/consumer/paymentEntity/get/{id}")
    public CommonResult<Payment> getPaymentEntityById(@PathVariable("id") Long id){
        log.info("进来-----------------0----------");
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<Payment>(444,"失败");
        }
    }
    @GetMapping("/consumer/payment/lb")
    public String lb(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances==null || instances.size()==0){
            return null;
        }
        ServiceInstance current=loadBalancer.instance(instances);
        URI uri = current.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }

}
