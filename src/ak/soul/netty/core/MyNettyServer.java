package ak.soul.netty.core;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyNettyServer {

	
	
	public static void main(String args[]) {
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(group);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.localAddress(new InetSocketAddress("localhost", 9999));
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
				
					socketChannel.pipeline().addLast(new HelloNettyServerHandler());
					
				}
			});
			System.out.println("Starting Netty Server ");
			ChannelFuture channelFuture =   bootstrap.bind().sync();
			System.out.println("Server Started");
			channelFuture.channel().closeFuture().sync();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
