package transport;

import entity.RpcRequest;
import entity.RpcResponse;
import transport.socket.client.SocketClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: rpc-framework
 * @description: 客户端实现的动态代理
 * @author: honghui
 * @create: 2022/05/15 18:11
 */
public class RpcClientProxy implements InvocationHandler {
    private String host;
    private int port;

    public RpcClientProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    /**
    *@Description: 获取代理对象
    *@Param:
    *@return: 
    *@Author: hong hui
    *@date: 
    */
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameters(args)
                .paramTypes(method.getParameterTypes())
                .build();
        SocketClient socketClient = new SocketClient();
        return ((RpcResponse) socketClient.sendRequest(rpcRequest, host, port)).getData();
    }
}
