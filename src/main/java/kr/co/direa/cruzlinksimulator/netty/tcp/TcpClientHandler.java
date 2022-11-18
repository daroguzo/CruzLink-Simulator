package tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.UUID;

public class TcpClientHandler extends ChannelInboundHandlerAdapter {

    private static final int LENGTH_PART_LENGTH = 8;
    private static final String ENCODING = "KSC5601";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String interfaceCode = "redistestSND";
        String message = "";
//        String guid = UUID.randomUUID().toString().substring(0,8);
        String guid = "e27111d3-49c7-425b-ae3b-085c_03_20220802083918022";

        // set the length part
        StringBuilder sendLengthPart = new StringBuilder();
        int bytesLength = (interfaceCode + guid + message).getBytes(ENCODING).length + LENGTH_PART_LENGTH;
//        int bytesLength = (interfaceCode + message).getBytes(ENCODING).length + LENGTH_PART_LENGTH;
        String lengthString = String.valueOf(bytesLength);
        for (int i = 0; i < LENGTH_PART_LENGTH - lengthString.length(); i++) {
            sendLengthPart.append(0);
        }
        sendLengthPart.append(lengthString);
        String sendData = sendLengthPart.toString() + interfaceCode + guid + message;
//        String sendData = sendLengthPart.toString() + interfaceCode + message;

        ByteBuf messageBuffer = Unpooled.buffer();
        messageBuffer.writeBytes(sendData.getBytes(ENCODING));
        System.out.println("보낸 문자열: [" + sendData + "]");
        ctx.writeAndFlush(messageBuffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String readMessage = ((ByteBuf)msg).toString(Charset.forName(ENCODING));
        System.out.println("받은 문자열: [" + readMessage + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.close();
//        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
