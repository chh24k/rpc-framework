package registry;

import java.net.InetSocketAddress;

/**
 * @author Honghui
 */
public interface ServiceDiscovery {
    /**
     * 查找服务
     * @param serviceName
     * @return
     */
    InetSocketAddress lookupService(String serviceName);
}
