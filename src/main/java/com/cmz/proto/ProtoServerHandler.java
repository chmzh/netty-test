
package com.cmz.proto;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protobuf.clazz.Protocol;
import protobuf.clazz.Protocol.Login;
import protobuf.clazz.Protocol.Request;

public class ProtoServerHandler extends ChannelInboundHandlerAdapter {
	private int count;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		Request request = (Request)msg;
		System.out.println("cmd:" + request.getCmdId());
		Login login = request.getExtension(Protocol.login);
		System.out.println("user:" + login.getUser());
		System.out.println("psw:" + login.getPswd());
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		ctx.close();
	}
}
