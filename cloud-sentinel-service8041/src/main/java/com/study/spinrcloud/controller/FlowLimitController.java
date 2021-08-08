package com.study.spinrcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {

    @GetMapping(value = "/testA")
    public String testA(){
        return "testA";
    }
    @GetMapping(value = "/testB")
    public String testB(){
        return "testB";
    }
    @GetMapping(value = "/testC")
    public String testC(){
        return "testA";
    }

}
