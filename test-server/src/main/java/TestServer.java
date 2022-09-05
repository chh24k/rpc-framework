
import transport.socket.server.SocketServer;
import provider.DefaultServiceProvider;
import provider.ServiceProvider;


/**
 * @program: rpc-framework
 * @description: testserver
 * @author: honghui
 * @create: 2022/05/15 18:52
 */
public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceProvider serviceProvider = new DefaultServiceProvider();
        serviceProvider.addServiceProvider(helloService, helloService.getClass().getName());
        SocketServer socketServer = new SocketServer(serviceProvider, 9001);
        socketServer.start();
    }
}
