package com.whu.rpc.servertest;

import anno.RpcService;
import api.HelloObject;
import api.HelloService;
import io.protostuff.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/09/05 19:56
 */
@Slf4j
@RpcService
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(HelloObject object) {
        log.info("Receive: {}", object.getMessage());
        return "Used return val, id = " + object.getId();
    }
}
