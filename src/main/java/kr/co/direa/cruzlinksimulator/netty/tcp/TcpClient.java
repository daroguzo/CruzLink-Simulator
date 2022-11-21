package kr.co.direa.cruzlinksimulator.netty.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import kr.co.direa.cruzlinksimulator.dto.Test;

public class TcpClient {

    private final Test test;

    public TcpClient(Test test) {
        this.test = test;
    }

    public void run() throws Exception{
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
                            pipeline.addLast(new TcpClientHandler(test.getEncoding().getValue(),
                                    test.getTestData()));
                        }
                    });
            // Start the client
            ChannelFuture future = bootstrap.connect(test.getHost(), Integer.parseInt(test.getPort())).sync();

            // Wait until the server socket is closed
            future.channel().closeFuture().sync();
        } finally {
            // Shut down all event loops to terminate all threads
            group.shutdownGracefully();
        }
    }
}
