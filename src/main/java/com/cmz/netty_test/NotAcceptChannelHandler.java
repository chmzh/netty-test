package com.cmz.netty_test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NotAcceptChannelHandler extends ChannelInboundHandlerAdapter {
	private static volatile boolean NOT_ACCEPT = false;
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String mString = (String)msg;
		if(mString.equals("not accept")){
			NOT_ACCEPT = true;
		}else if(mString.equals("close server")){
			stopServer(ctx);
			return;
		}
		if(NOT_ACCEPT){
			ctx.channel().closeFuture().sync();
			return;
		}
		
		ctx.fireChannelRead(msg);
	}
	
	//关闭服务器
	public void stopServer(ChannelHandlerContext ctx){
		ctx.channel().parent().close();
	}
}
