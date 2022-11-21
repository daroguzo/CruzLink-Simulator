package kr.co.direa.cruzlinksimulator.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.Charset;

public class TcpServerHandler extends ChannelInboundHandlerAdapter {

    private final String encoding;

    public TcpServerHandler(String encoding) {
        this.encoding = encoding;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String message = ((ByteBuf)msg).toString(Charset.forName(encoding));

        ByteBuf messageBuffer = Unpooled.buffer();
        messageBuffer.writeBytes(message.getBytes(encoding));

        ctx.channel().write(messageBuffer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();

    }
}