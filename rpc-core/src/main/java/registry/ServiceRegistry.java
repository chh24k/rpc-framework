package registry;

import java.net.InetSocketAddress;

/**
 * 服务注册
 * @author Honghui
 */
public interface ServiceRegistry {

    /**
     * 在注册中心注册服务
     * @param serviceName
     * @param inetSocketAddress
     */
    void register(String serviceName, InetSocketAddress inetSocketAddress);

}
