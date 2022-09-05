package registry;

import balance.LoadBalancer;
import balance.RoundRobinLoadBalancer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import enumeration.RpcError;
import exception.RpcException;
import org.objectweb.asm.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.NacosUtil;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/09/05 16:47
 */
public class NacosServiceDiscovery implements ServiceDiscovery{

    private static final Logger logger = LoggerFactory.getLogger(NacosServiceDiscovery.class);
    private final LoadBalancer loadBalancer;

    public NacosServiceDiscovery(LoadBalancer loadBalancer) {
        if(loadBalancer == null) {
            this.loadBalancer = new RoundRobinLoadBalancer();
        } else {
            this.loadBalancer = loadBalancer;
        }
    }
    @Override
    public InetSocketAddress lookupService(String serviceName) {
        try {
            List<Instance> allInstance = NacosUtil.getAllInstance(serviceName);
            if(allInstance.size() == 0) {
                logger.error("找不到对应的服务" + serviceName);
                throw new RpcException(RpcError.SERVICE_NOT_FIND);
            }
            Instance instance = loadBalancer.select(allInstance);
            return new InetSocketAddress(instance.getIp(), instance.getPort());
        } catch (NacosException e) {
            logger.error("获取服务时有错误发生", e);
        }
        return null;
    }
}
