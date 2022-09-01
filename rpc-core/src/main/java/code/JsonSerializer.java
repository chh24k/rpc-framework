package code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.RpcRequest;
import enumeration.SerializerCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/09/01 19:31
 */
public class JsonSerializer implements CommonSerializer{

    private static final Logger logger = LoggerFactory.getLogger(JsonSerializer.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public int getCode() {
        return SerializerCode.valueOf("JSON").getCode();
    }

    @Override
    public byte[] serialize(Object o)  {
        try {
            return objectMapper.writeValueAsBytes(o);
        } catch (JsonProcessingException e) {
            logger.error("序列化错误：{}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object deserialize(byte[] bytes, Class<?> packageClass) {
        try {
            Object o = objectMapper.readValue(bytes, packageClass);
            if(o instanceof RpcRequest) {
                o = handleRequest(o);
            }
            return o;
        } catch (IOException e) {
            logger.error("反序列化错误：{}", e.getMessage());
            e.getMessage();
            return null;
        }
    }

    private Object handleRequest(Object o) throws IOException {
        RpcRequest rpcRequest = (RpcRequest) o;
        for(int i = 0; i < rpcRequest.getParamTypes().length; i++) {
            Class<?> clazz = rpcRequest.getParamTypes()[i];
            if(!clazz.isAssignableFrom(rpcRequest.getParameters()[i].getClass())) {
                byte[] bytes = objectMapper.writeValueAsBytes(rpcRequest.getParameters()[i]);
                rpcRequest.getParameters()[i] = objectMapper.readValue(bytes, clazz);
            }
        }
        return rpcRequest;
    }
}
