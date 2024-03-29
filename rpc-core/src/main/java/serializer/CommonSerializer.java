package serializer;


import java.util.concurrent.CancellationException;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/09/01 19:02
 */

public interface CommonSerializer {

    Integer KRYO_SERIALIZER = 0;
    Integer JSON_SERIALIZER = 1;
    Integer HESSIAN_SERIALIZER = 2;
    Integer PROTOBUF_SERIALIZER = 3;

    Integer DEFAULT_SERIALIZER = KRYO_SERIALIZER;

    int getCode();

    static CommonSerializer getByCode(int code) {
        switch (code) {
            case 0:
                return new KryoSerializer();
            case 1:
                return new JsonSerializer();
            case 2:
                return new HessianSerializer();
            case 3:
                return new ProtobufSerializer();
            default:
                return null;
        }
    }

    byte[] serialize(Object o);

    Object deserialize(byte[] bytes, Class<?> packageClass);
}
