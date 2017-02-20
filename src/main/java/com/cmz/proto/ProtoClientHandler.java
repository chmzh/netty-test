
package com.cmz.proto;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protobuf.clazz.Protocol;
import protobuf.clazz.Protocol.Login;
import protobuf.clazz.Protocol.Request;

import java.util.logging.Logger;


public class ProtoClientHandler extends ChannelInboundHandlerAdapter {
	
	private static final Logger logger = Logger.getLogger(ProtoClientHandler.class.getName());

	

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		for(int i=0;i<100;i++){
			Login login = Login.newBuilder().setUser("ksfzhaohui")
					.setPswd("11111111").build();
			Request.Builder builder = Request.newBuilder();
			builder.setCmdId(10001);
			builder.setExtension(Protocol.login, login);
			Request request = builder.build();

			ctx.writeAndFlush(request);
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String string = (String)msg;
		System.out.println(string);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// 释放资源
		logger.warning("Unexpected exception from downstream : " + cause.getMessage());
		ctx.close();
	}
}
