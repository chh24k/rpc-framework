package com.whu.rpc.servertest;

import anno.RpcServiceScan;
import api.HelloService;
import transport.netty.server.NettyServer;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/09/01 21:31
 */
@RpcServiceScan
public class NettyTestServer {

    public static void main(String[] args) {
//        HelloService helloService = new HelloServiceImpl();
//        NettyServer server = new NettyServer("127.0.0.1", 10001);
//        server.publishService(helloService, api.HelloService.class);
//        server.start(10001);

        NettyServer server = new NettyServer("127.0.0.1", 10001);
        server.start();
    }
}
