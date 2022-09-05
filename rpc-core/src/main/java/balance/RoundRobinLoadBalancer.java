package balance;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.io.PipedReader;
import java.util.List;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/09/03 20:02
 */
public class RoundRobinLoadBalancer implements LoadBalancer{

    private int index = 0;

    @Override
    public Instance select(List<Instance> instances) {
        if(index >= instances.size()) {
            index %= instances.size();
        }
        return instances.get(index);
    }
}
