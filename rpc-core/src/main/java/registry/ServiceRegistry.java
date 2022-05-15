package registry;

/**
 * @author Honghui
 */
public interface ServiceRegistry {
    <T> void register(T service);
    Object getService(String serviceName);
}
