package entity;

import com.sun.jdi.PrimitiveValue;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: rpc-framework
 * @description: 请求实体
 * @author: honghui
 * @create: 2022/05/15 18:00
 */
@Data
@Builder
public class RpcRequest implements Serializable {
    private String interfaceName;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
}
