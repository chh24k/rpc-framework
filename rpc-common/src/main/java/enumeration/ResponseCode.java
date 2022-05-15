package enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Honghui
 */

@Getter
@AllArgsConstructor
public enum ResponseCode {
    success(200,"OK");


    private final Integer code;
    private final String message;
}
