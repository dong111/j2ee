package cn.dong111.book.chapter1.mina;


import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.mina.common.IoAcceptor;
import org.apache.mina.common.IoHandler;
import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.SocketAcceptor;

/**
 * 描述：基于Mina实现的服务器端
 *
 * @author bluedavy
 * 创建时间： 2008-12-4
 */
public class Server {

	public static void main(String[] args) throws Exception{
		int port=9527;
		//创建一个线程池大小为cpu核数+1的SocketConnector对象
		final IoAcceptor acceptor=new SocketAcceptor(Runtime.getRuntime().availableProcessors() + 1,
				Executors.newCachedThreadPool());
		acceptor.getFilterChain().addLast("stringserialize", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		IoHandler handler=new IoHandlerAdapter(){

			public void messageReceived(IoSession session, Object message)
					throws Exception {
				//接收客户端发送的对象
				if("quit".equalsIgnoreCase(message.toString())){
					acceptor.unbindAll();
					System.out.println("Server has been shutdown!");
					System.exit(0);
				}
				System.out.println("Message from client: "+message);
				session.write("Server response："+message);
			}

		};
		//绑定监听的端口以及当有连接建立，接收对象等待事件发送时需要通知的ToHandler对象
		acceptor.bind(new InetSocketAddress(port), handler);
		System.out.println("Server listen on port: "+port);
	}

}
