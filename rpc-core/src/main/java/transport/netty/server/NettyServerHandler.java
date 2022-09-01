package transport.netty.server;

import entity.RpcRequest;
import entity.RpcResponse;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import model.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import registry.DefaultScratchServiceRegistry;
import registry.ScratchServiceRegistry;

/**
 * @program: rpc-framework
 * @description:
 * @author: honghui
 * @create: 2022/09/01 19:59
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<RpcRequest> {

    public static final Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);
    private static RequestHandler requestHandler;
    private static ScratchServiceRegistry serviceRegistry;

    static {
        requestHandler = new RequestHandler();
        serviceRegistry = new DefaultScratchServiceRegistry();
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcRequest rpcRequest) throws Exception {
        try {
            logger.info("服务器接收到请求：{}", rpcRequest);
            String interfaceName = rpcRequest.getInterfaceName();
            Object service = serviceRegistry.getService(interfaceName);
            Object result = requestHandler.handle(service, rpcRequest);
            ChannelFuture future = channelHandlerContext.writeAndFlush(RpcResponse.success(result));
            future.addListener(ChannelFutureListener.CLOSE);
        } finally {
            ReferenceCountUtil.release(rpcRequest);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("处理过程调用时有错误发生:", cause.getMessage());
        cause.printStackTrace();
        ctx.close();
    }
}
