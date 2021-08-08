package com.study.spinrcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.study.springboot.entities.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult handleException(BlockException e){
        return new CommonResult(444,"自定义错误处理1");
    }

    public static CommonResult handleException2(BlockException e){
        return new CommonResult(444,"自定义错误处理2");
    }
}
