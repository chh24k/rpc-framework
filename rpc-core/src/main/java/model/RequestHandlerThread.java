package model;

import entity.RpcRequest;
import entity.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import registry.ServiceRegistry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/05/15 20:31
 */
public class RequestHandlerThread implements Runnable{

    public static final Logger logger = LoggerFactory.getLogger(RequestHandlerThread.class);

    private Socket socket;
    private RequestHandler handler;
    private ServiceRegistry serviceRegistry;

    public RequestHandlerThread(Socket socket, RequestHandler handler, ServiceRegistry serviceRegistry) {
        this.socket = socket;
        this.handler = handler;
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void run() {
        try(ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());) {
            RpcRequest rpcRequest = (RpcRequest) in.readObject();
            Object service = serviceRegistry.getService(rpcRequest.getInterfaceName());
            Object result = handler.handle(service, rpcRequest);
            out.writeObject(RpcResponse.success(result));
            out.flush();
            logger.info("worker正常运行");
        } catch (IOException | ClassNotFoundException e) {
            logger.error("处理线程处异常：" + e);
        }
    }
}
