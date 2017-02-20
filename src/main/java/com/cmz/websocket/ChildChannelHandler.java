package com.cmz.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
public class ChildChannelHandler extends ChannelInitializer<SocketChannel>{
	@Override
	protected void initChannel(SocketChannel e) throws Exception {
		
		e.pipeline().addLast(new HttpServerCodec());
		e.pipeline().addLast(new HttpObjectAggregator(65536));
		e.pipeline().addLast(new ChunkedWriteHandler());
		e.pipeline().addLast(new MyWebSocketServerHandler());
	}
} 
