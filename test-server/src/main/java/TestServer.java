
import transport.socket.server.SocketServer;
import registry.ScratchDefaultScratchServiceRegistry;
import registry.ScratchServiceRegistry;


/**
 * @program: rpc-framework
 * @description: testserver
 * @author: honghui
 * @create: 2022/05/15 18:52
 */
public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ScratchServiceRegistry scratchServiceRegistry = new ScratchDefaultScratchServiceRegistry();
        scratchServiceRegistry.register(helloService);
        SocketServer socketServer = new SocketServer(scratchServiceRegistry);
        socketServer.start(9001);
    }
}
