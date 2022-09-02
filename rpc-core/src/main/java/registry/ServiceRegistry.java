package registry;

import java.net.InetSocketAddress;

/**
 * 服务注册
 * @author Honghui
 */
public interface ServiceRegistry {

    //
    void register(String serviceName, InetSocketAddress inetSocketAddress);

    //
    InetSocketAddress lookupService(String serviceName);
}
