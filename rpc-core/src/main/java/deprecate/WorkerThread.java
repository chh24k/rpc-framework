package deprecate;

import entity.RpcRequest;
import entity.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @program: rpc-framework
 * @description: 工作调用线程
 * @author: honghui
 * @create: 2022/05/15 18:39
 */
@Deprecated
public class WorkerThread implements Runnable{

    private Socket socket;
    private Object service;
    public static final Logger logger = LoggerFactory.getLogger(WorkerThread.class);

    public WorkerThread(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        try(ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());) {
            RpcRequest rpcRequest = (RpcRequest) in.readObject();
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
            Object result = method.invoke(service, rpcRequest.getParameters());
            out.writeObject(RpcResponse.success(result, rpcRequest.getRequestId()));
            out.flush();
        } catch (Exception e) {
            logger.error("worker线程调用时出错", e);
        }
    }
}
