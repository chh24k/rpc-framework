package enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RpcError {
    //
    SERVICE_NOT_FIND("服务未找到"),
    SERVICE_NOT_IMPLEMENT_ANY_INTERFACE("服务未实现任何接口"),
    UNKNOWN_PROTOCOL("不能识别的协议"),
    UNKNOWN_PACKAGE_TYPE("不能识别的服务包"),
    UNKNOWN_SERIALIZER("不能识别的反序列化器"),
    FAILED_TO_CONNECT_TO_SERVICE_REGISTRY("连接注册中心异常"),
    REGISTER_SERVICE_FAILED("注册失败"),
    FAILED_TO_FIND_SERVICE("获取服务失败"),
    SERIALIZER_NOT_FOUND("未找到序列化器")
    ;

    private final String message;
}
