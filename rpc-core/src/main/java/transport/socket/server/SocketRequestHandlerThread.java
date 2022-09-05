package transport.socket.server;

import entity.RpcRequest;
import entity.RpcResponse;
import handler.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import provider.ServiceProvider;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/05/15 20:31
 */
public class SocketRequestHandlerThread implements Runnable{

    public static final Logger logger = LoggerFactory.getLogger(SocketRequestHandlerThread.class);

    private Socket socket;
    private RequestHandler handler;
    private ServiceProvider serviceProvider;

    public SocketRequestHandlerThread(Socket socket, RequestHandler handler, ServiceProvider serviceProvider) {
        this.socket = socket;
        this.handler = handler;
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void run() {
        try(ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());) {
            RpcRequest rpcRequest = (RpcRequest) in.readObject();
            Object service = serviceProvider.getServiceProvider(rpcRequest.getInterfaceName());
            Object result = handler.handle(service, rpcRequest);
            out.writeObject(RpcResponse.success(result, rpcRequest.getRequestId()));
            out.flush();
            logger.info("worker正常运行");
        } catch (IOException | ClassNotFoundException e) {
            logger.error("处理线程处异常：" + e);
        }
    }
}
