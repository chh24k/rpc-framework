package enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RpcError {
    SERVICE_NOT_FIND("服务未找到"),
    SERVICE_NOT_IMPLEMENT_ANY_INTERFACE("服务未实现任何接口");

    private final String message;
}
