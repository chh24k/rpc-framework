import anno.RpcServiceScan;
import provider.DefaultServiceProvider;
import provider.ServiceProvider;
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
        HelloService helloService = new HelloServiceImpl();
//        NettyServer server = new NettyServer("127.0.0.1", 10001);
//        server.publishService(helloService, HelloService.class);
//        server.start(10001);
        NettyServer server = new NettyServer("127.0.0.1", 10001);
        server.publishService(helloService);
        server.start();
    }
}
