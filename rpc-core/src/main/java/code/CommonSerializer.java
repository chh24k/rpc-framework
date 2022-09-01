package code;


/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/09/01 19:02
 */

public interface CommonSerializer {

    int getCode();

    static CommonSerializer getByCode(int code) {
        switch (code) {
            case 1:
                return new JsonSerializer();
            default:
                return null;
        }
    }

    byte[] serialize(Object o);

    Object deserialize(byte[] bytes, Class<?> packageClass);
}
