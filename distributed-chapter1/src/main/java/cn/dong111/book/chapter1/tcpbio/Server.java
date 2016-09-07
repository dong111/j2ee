package cn.dong111.book.chapter1.tcpbio;
/**
 * 《构建高性能的大型分布式Java应用》
 *  书中的示例代码
 *  版权所有   2008---2009
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 描述：基于java实现TCP/IP方式的网络通讯示例，此为服务器端代码
 *
 * @modify chendong
 *2016-09-07
 *缺点：服务器无法同时接受多个连接请求
 * 简单解决方法:服务器和客户端都创建多个接收和发送的socket连接池
 *仍软存在问题：多个请求连接时候，没创建和接收一个scoket都会消耗一个线程,需要限制创建线程数量（否者导致服务器端资源耗尽）。
 * 结论:这就造成采用BIO的情况下服务器端所能支撑的连接数是有限的
 *
 * @author bluedavy 
 * 
 * 创建时间： 2008-11-27
 */
public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		int port=9527;
		ServerSocket ss=new ServerSocket(port);
		System.out.println("Server listen on port: "+port);
		Socket socket=ss.accept();
		BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
		while(true){
			String line=in.readLine();
			if(line==null){
				Thread.sleep(100);
				continue;
			}
			if("quit".equalsIgnoreCase(line.trim())){
				in.close();
				out.close();
				ss.close();
				System.out.println("Server has been shutdown!");
				System.exit(0);
			}
			else{
				System.out.println("Message from client: "+ line);
				out.println("Server response："+line);
				Thread.sleep(100);
			}
		}
	}
	
}
