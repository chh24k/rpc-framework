package exception;

import enumeration.RpcError;

/**
 * @program: rpc-framework
 * @description: 自定义异常
 * @author: honghui
 * @create: 2022/05/15 20:00
 */
public class RpcException extends RuntimeException {

    public RpcException(RpcError error, String detail) {
        super(error.getMessage() + ":" + detail);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcException(RpcError error) {
        super(error.getMessage());
    }

}
