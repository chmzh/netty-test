
package com.cmz.netty_test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.SucceededFuture;

import java.util.logging.Logger;


public class TimeClientHandler extends ChannelInboundHandlerAdapter {
	private int count;
	private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());

	byte[] req;

	/**
	 * Creates a client-side handler.
	 */
	public TimeClientHandler() {
		req = "QUERY TIME ORDER".getBytes();

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		ByteBuf firstMessage;
		//for (int i = 0; i < 100; i++) {
			 firstMessage = Unpooled.buffer(4+req.length);
			// firstMessage.writeInt(req.length);
			// firstMessage.writeBytes(req);
			// ctx.writeAndFlush(firstMessage);
			
			ChannelFuture future = ctx.writeAndFlush("QUERY TIME ORDER");
			future.addListener(new ChannelFutureListener() {
				
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					if(future.isSuccess()){
						System.out.println("发送成功");
						future.channel().close();
					}
					
				}
			});
		//}

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req, "UTF-8");
		System.out.println("Now is : " + body+".count="+count++);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// 释放资源
		logger.warning("Unexpected exception from downstream : " + cause.getMessage());
		ctx.close();
	}
}
