package factory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.ObjectName;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/09/05 23:09
 */
public class SingletonFactory {

    private static volatile Map<Class, Object> objectMap = new HashMap<>();

    private SingletonFactory() {
    }

    public static <T> T getInstance(Class<T> clazz) {
        Object instance = objectMap.get(clazz);
        if (instance == null) {
            synchronized (clazz) {
                instance = objectMap.get(clazz);
                if (instance == null) {
                    try {
                        instance = clazz.newInstance();
                        objectMap.put(clazz, instance);
                    } catch (IllegalAccessException | InstantiationException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                }
            }
        }
        return clazz.cast(instance);
    }
}
