package kr.co.direa.cruzlinksimulator.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import kr.co.direa.cruzlinksimulator.dto.TestData;

import java.nio.charset.Charset;

public class TcpClientHandler extends ChannelInboundHandlerAdapter {
    private final String encoding;
    private final TestData testData;
    private final String message;

    public TcpClientHandler(String encoding, TestData testData) {
        this.encoding = encoding;
        this.testData = testData;
        this.message = testData.getHeader() + testData.getData();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf messageBuffer = Unpooled.buffer();
        messageBuffer.writeBytes(message.getBytes(encoding));
        System.out.println("보낸 문자열: [" + message + "]");
        ctx.writeAndFlush(messageBuffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String readMessage = ((ByteBuf)msg).toString(Charset.forName(encoding));
        System.out.println("받은 문자열: [" + readMessage + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
