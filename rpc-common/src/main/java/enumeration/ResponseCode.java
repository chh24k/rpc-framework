package enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Honghui
 */

@Getter
@AllArgsConstructor
public enum ResponseCode {
    //
    SUCCESS(200,"OK"),
    //
    METHOD_NOT_FOUND(301, "未找到方法");


    private final Integer code;
    private final String message;
}
