package handler;

import entity.RpcRequest;
import entity.RpcResponse;
import enumeration.ResponseCode;
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

    /**
    *@Description: 在catch处返回fail
    *@Param:
    *@return: 
    *@Author: hong hui
    *@date: 
    */
    public Object handle(Object service, RpcRequest rpcRequest) {
        Object result;
        logger.info("进入handler");
        try {
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
            result = method.invoke(service, rpcRequest.getParameters());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            logger.error("处理器错误：" + e);
            return RpcResponse.fail(ResponseCode.METHOD_NOT_FOUND, rpcRequest.getRequestId());
        }
        return result;
    }
}
