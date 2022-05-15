package model;

import entity.RpcRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/05/15 20:28
 */
public class RequestHandler {

    public static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    public Object handle(Object service, RpcRequest rpcRequest) {
        String methodName = rpcRequest.getMethodName();
        logger.info("进入handler");
        try {
            Method method = service.getClass().getMethod(methodName, rpcRequest.getParamTypes());
            Object result = method.invoke(service, rpcRequest.getParameters());
            return result;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            logger.error("处理器错误：" + e);
            return null;
        }
    }
}
