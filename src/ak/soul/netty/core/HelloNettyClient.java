package ak.soul.netty.core;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class HelloNettyClient {
	
	public static void main(String args[]) {
		
		
		try {
			Bootstrap clientBootstarp = new Bootstrap();
			EventLoopGroup eventGroupLoop =    new NioEventLoopGroup();
			clientBootstarp.group(eventGroupLoop);
			clientBootstarp.channel(NioSocketChannel.class);
			clientBootstarp.remoteAddress(new InetSocketAddress("localhost", 9999));
			clientBootstarp.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new ClientHandler());
					
				}
			});
			ChannelFuture channelFuture =  clientBootstarp.connect().sync();
			channelFuture.channel().closeFuture().sync();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}

}
