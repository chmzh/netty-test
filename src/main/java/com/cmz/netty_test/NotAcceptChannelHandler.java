package com.cmz.netty_test;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NotAcceptChannelHandler extends ChannelInboundHandlerAdapter {
	private static volatile boolean NOT_ACCEPT = false;
	private static volatile boolean CLOSE_SERVER = false;
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if(CLOSE_SERVER){
			return;
		}
		
		String mString = (String)msg;
		if(mString.equals("not accept")){
			NOT_ACCEPT = true;
		}if(mString.equals("close server")){
			CLOSE_SERVER = true;
			stopServer(ctx);
			return;
		}
		
		if(NOT_ACCEPT){
			Channel channel = ctx.channel();
			ChannelFuture future = channel.closeFuture();
			future.sync();
			return;
		}
		
		
		
		ctx.fireChannelRead(msg);
	}
	
	//关闭服务器
	public void stopServer(ChannelHandlerContext ctx){
		ctx.channel().parent().close();
	}
}
