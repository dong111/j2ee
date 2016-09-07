package cn.dong111.book.chapter1.mina;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.mina.common.ConnectFuture;
import org.apache.mina.common.IoHandler;
import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.SocketConnector;
import org.apache.mina.transport.socket.nio.SocketConnectorConfig;

/**
 * 描述：基于Mina实现的tcp client
 *
 * @author bluedavy
 * 创建时间： 2008-12-4
 */
public class Client {

	public static void main(String[] args) throws Exception{
		int port=9527;
		//创建一个线程池大小为cpu核数+1的SocketConnector对象
		SocketConnector ioConnector = new SocketConnector(Runtime.getRuntime().availableProcessors() + 1,
				Executors.newCachedThreadPool());
		ioConnector.getDefaultConfig().getSessionConfig().setTcpNoDelay(true);
		//增加一个将发送对象进行序列化以及接收字节流进行反序列化的类到filter chain
		ioConnector.getFilterChain().addLast("stringserialize", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", port);
        //TOHandler的实现，以便当mina建立连接，接收消息后通知应用
		IoHandler handler=new IoHandlerAdapter(){

			public void messageReceived(IoSession session, Object message)
					throws Exception {
				System.out.println(message);
			}

		};
        //异步建立连接
		ConnectFuture connectFuture = ioConnector.connect(socketAddress,handler);
        //阻塞等待连接建立完成，如果需要设置连接创建的超时时间，可以调用

		connectFuture.join();
		IoSession session=connectFuture.getSession();
		BufferedReader systemIn=new BufferedReader(new InputStreamReader(System.in));
		while(true){
			String command=systemIn.readLine();
			if(command==null || "quit".equalsIgnoreCase(command.trim())){
				System.out.println("Client quit!");
				session.write("quit");
				session.close();
				System.exit(0);
			}
            //发送对象
			session.write(command);
		}
	}

}