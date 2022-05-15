
import model.RpcServer;
import registry.DefaultServiceRegistry;
import registry.ServiceRegistry;


/**
 * @program: rpc-framework
 * @description: testserver
 * @author: honghui
 * @create: 2022/05/15 18:52
 */
public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        RpcServer rpcServer = new RpcServer(serviceRegistry);
        rpcServer.start(9001);
    }
}
