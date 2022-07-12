import transport.RpcClientProxy;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/05/15 19:09
 */
public class TestClient {
    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy("127.0.0.1", 9001);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject obj = new HelloObject(2021, "This is what i come for");
        String res = helloService.hello(obj);
        System.out.println(res);
    }
}
