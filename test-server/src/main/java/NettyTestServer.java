import registry.DefaultScratchServiceRegistry;
import registry.ScratchServiceRegistry;
import transport.netty.server.NettyServer;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/09/01 21:31
 */
public class NettyTestServer {

    public static void main(String[] args) {
        HelloServiceImpl helloService = new HelloServiceImpl();
        ScratchServiceRegistry registry = new DefaultScratchServiceRegistry();
        registry.register(helloService);
        NettyServer server = new NettyServer();
        server.start(10001);
    }
}
