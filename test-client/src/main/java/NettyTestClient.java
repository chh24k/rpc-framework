import transport.RpcClientProxy;
import transport.netty.client.NettyClient;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/09/01 20:41
 */
public class NettyTestClient {

    public static void main(String[] args) {
        NettyClient client = new NettyClient();
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }
}
