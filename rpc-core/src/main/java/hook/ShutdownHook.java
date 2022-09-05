package hook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.NacosUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/09/03 19:54
 */
public class ShutdownHook {

    private static final Logger logger = LoggerFactory.getLogger(ShutdownHook.class);

    private final ExecutorService threadPool = Executors.newFixedThreadPool(10);
    private static final ShutdownHook shutdownHook = new ShutdownHook();

    public static ShutdownHook getShutdownHook() {
        return shutdownHook;
    }

    public void addClearAllHook() {
        logger.info("关闭后将自动注销所有服务");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            NacosUtil.clearRegistry();
            threadPool.shutdown();
        }));
    }
}
