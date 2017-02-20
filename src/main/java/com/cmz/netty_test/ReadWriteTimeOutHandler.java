package com.cmz.netty_test;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class ReadWriteTimeOutHandler extends ChannelDuplexHandler {
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		IdleStateEvent e = (IdleStateEvent)evt;
		if(e.state() == IdleState.READER_IDLE){
			System.out.println("IdleState.READER_IDLE");
			//TODO 处理读超时
		}else if(e.state() == IdleState.WRITER_IDLE){
			System.out.println("IdleState.WRITER_IDLE");
			//TODO 处理写超时
		}
	}
}
