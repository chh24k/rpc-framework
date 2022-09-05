package serializer;

import enumeration.SerializerCode;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.nio.channels.ShutdownChannelGroupException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/09/05 14:45
 */
public class ProtobufSerializer implements CommonSerializer{

    private LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
    private Map<Class<?>, Schema<?>> schemaCache = new ConcurrentHashMap<>();

    @Override
    public int getCode() {
        return SerializerCode.valueOf("PROTOBUF").getCode();
    }

    @Override
    public byte[] serialize(Object o) {
        Class clazz = o.getClass();
        Schema schema = getSchema(clazz);
        byte[] data;
        try {
            data = ProtostuffIOUtil.toByteArray(o, schema, buffer);
        } finally {
            buffer.clear();
        }
        return data;
    }

    @Override
    public Object deserialize(byte[] bytes, Class<?> packageClass) {
        Schema schema = getSchema(packageClass);
        Object o = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, o, schema);
        return o;
    }

    private Schema getSchema(Class clazz) {
        Schema<?> schema = schemaCache.get(clazz);
        if(Objects.isNull(schema)) {
            schema = RuntimeSchema.getSchema(clazz);
            if(Objects.nonNull(schema)) {
                schemaCache.put(clazz, schema);
            }
        }
        return schema;
    }
}
