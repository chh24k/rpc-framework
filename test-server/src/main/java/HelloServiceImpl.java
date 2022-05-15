import lombok.extern.slf4j.Slf4j;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/05/15 17:58
 */
@Slf4j
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(HelloObject object) {
        log.info("Receive: {}", object.getMessage());
        return "Used return val, id = " + object.getId();
    }
}
