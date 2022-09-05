package provider;

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
public class DefaultServiceProvider implements ServiceProvider {

    public static final Logger logger = LoggerFactory.getLogger(DefaultServiceProvider.class);

    private static final ConcurrentHashMap<String, Object> serviceMap = new ConcurrentHashMap<>();
    private static final Set<String> provideService = ConcurrentHashMap.newKeySet();

    @Override
    public void addServiceProvider(Object service, String serviceName) {
        if(provideService.contains(serviceName)) {
            return;
        }
        provideService.add(serviceName);
        serviceMap.put(serviceName, service);
        logger.info("向接口：{} 注册服务：{}", service.getClass().getInterfaces(), serviceName);
    }

    @Override
    public Object getServiceProvider(String serviceName) {
        Object service = serviceMap.get(serviceName);
        if(service == null) {
            throw new RpcException(RpcError.SERVICE_NOT_FIND);
        }
        return service;
    }
}
