package registry;

import enumeration.RpcError;
import exception.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/05/15 19:55
 */
public class DefaultServiceRegistry implements ServiceRegistry {

    public static final Logger logger = LoggerFactory.getLogger(DefaultServiceRegistry.class);

    private final ConcurrentHashMap<String, Object> serviceMap = new ConcurrentHashMap<>();
    private final Set<String> registerService = ConcurrentHashMap.newKeySet();

    @Override
    public synchronized <T> void register(T service) {
        String serviceName = service.getClass().getCanonicalName();
        if(registerService.contains(serviceName)) {
            return;
        }
        registerService.add(serviceName);
        Class<?>[] interfaces = service.getClass().getInterfaces();
        if(interfaces.length == 0) {
            throw new RpcException(RpcError.SERVICE_NOT_IMPLEMENT_ANY_INTERFACE);
        }

        for(Class<?> clazz : interfaces) {
            serviceMap.put(clazz.getCanonicalName(), service);
        }
        logger.info("向接口：{} 注册服务：{}", interfaces, serviceName);
    }

    @Override
    public Object getService(String serviceName) {
        Object service = serviceMap.get(serviceName);
        if(service == null) {
            throw new RpcException(RpcError.SERVICE_NOT_FIND);
        }
        return service;
    }
}
