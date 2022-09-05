import api.HelloObject;
import api.HelloService;
import transport.RpcClient;
import transport.RpcClientProxy;
import transport.socket.client.SocketClient;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/05/15 19:09
 */
public class TestClient {
    public static void main(String[] args) {
        RpcClient client = new SocketClient("127.0.0.1", 9001);
        RpcClientProxy proxy = new RpcClientProxy(client);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject obj = new HelloObject(2021, "This is what i come for");
        String res = helloService.hello(obj);
        System.out.println(res);
    }
}
