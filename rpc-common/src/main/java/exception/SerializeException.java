package exception;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/09/02 00:18
 */
public class SerializeException extends RuntimeException{

    public SerializeException(String msg) {
        super(msg);
    }
}
