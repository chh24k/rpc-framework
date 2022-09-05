package com.whu.rpc.servertest;

import anno.RpcService;
import api.EndService;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/09/05 20:24
 */

@RpcService
@Slf4j
public class EndServiceImpl implements EndService {

    @Override
    public String end(String name) {
        log.info("客户端{}请求结束", name);
        return "服务端接收到结束请求";
    }
}
