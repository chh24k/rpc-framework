package provider;

/**
 * @author Honghui
 */
public interface ServiceProvider {
    /**
     * 注册服务
     * @param service
     * @param <T>
     */
    void addServiceProvider(Object service, String serviceName);

    /**
     * 获取服务
     * @param serviceName
     * @return
     */
    Object getServiceProvider(String serviceName);
}
