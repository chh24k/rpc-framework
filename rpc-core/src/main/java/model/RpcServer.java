package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import registry.ServiceRegistry;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ServiceConfigurationError;
import java.util.concurrent.*;

/**
 * @program: rpc-framework
 * @description: 服务端实现 反射
 * @author: honghui
 * @create: 2022/05/15 18:28
 */
public class RpcServer {
    public static final Logger logger = LoggerFactory.getLogger(RpcServer.class);

    private final ExecutorService threadPool;
    private final int CORE_POOL_SIZE = 5;
    private final int MAXIMUM_POOL_SIZE = 50;
    private final long KEEP_ALIVE_TIME = 60;
    private final int BLOCKING_QUEUE_CAPACITY = 100;

    private RequestHandler requestHandler = new RequestHandler();
    private final ServiceRegistry serviceRegistry;


    public RpcServer(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, workingQueue, threadFactory);
    }

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            logger.info("服务器启动");
            Socket socket;
            while((socket = serverSocket.accept()) != null) {
                logger.info("客户端连接，IP：" + socket.getInetAddress());
                threadPool.execute(new RequestHandlerThread(socket, requestHandler, serviceRegistry));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }










    /**
    *@Description: 注册单个服务
    *@Param: service 服务对象，是一个接口实现类
     * 通过反射 可以直接
    *@return: null
    *@Author: hong hui
    *@date:
    */
    @Deprecated
    public void register(Object service, int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            logger.info("服务器启动");
            Socket socket;
            while((socket = serverSocket.accept()) != null) {
                logger.info("客户端连接，IP：" + socket.getInetAddress());
                threadPool.execute(new WorkerThread(socket, service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
