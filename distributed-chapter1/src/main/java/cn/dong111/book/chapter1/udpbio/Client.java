/**
 * 《构建高性能的大型分布式Java应用》
 *  书中的示例代码
 *  版权所有   2008---2009
 */
package cn.dong111.book.chapter1.udpbio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 描述：基于java实现UDP/IP+BIO方式的网络通讯示例，此为客户端代码
 *
 *
 *
 *
 * @author bluedavy
 *
 * 创建时间： 2008-11-27
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		int port=9527;
		int aport=9528;
		//由于UIP/IP是无连接的,如果希望双向通讯，就必须启动一个监听端口，承担服务器的职责，如果不能绑定到指定的端口，则抛出SocketException
		DatagramSocket serverSocket=new DatagramSocket(aport);
		byte[] buffer=new byte[65507];
		DatagramPacket receivePacket=new DatagramPacket(buffer,buffer.length);
		DatagramSocket socket=new DatagramSocket();
		InetAddress server=InetAddress.getByName("localhost");
		BufferedReader systemIn=new BufferedReader(new InputStreamReader(System.in));
		boolean flag=true;
		while(flag){
			String command=systemIn.readLine();
			byte[] datas=command.getBytes("UTF-8");
			DatagramPacket packet=new DatagramPacket(datas,datas.length,server,port);
			//阻塞发送packet到指定的服务器端口，当出现网络IO异常，抛出IOExc。当连接不上端口地址抛出PortUnreachableExc
			socket.send(packet);
			if(command==null || "quit".equalsIgnoreCase(command.trim())){
				flag=false;
				System.out.println("Client quit!");
				socket.close();
				continue;
			}
			//阻塞并且同步读取流信息，如果接受的流信息比receivePacket长度长，则删除更长的信息
			serverSocket.receive(receivePacket);
			String receiveResponse=new String(receivePacket.getData(),0,receivePacket.getLength(),"UTF-8");
			System.out.println(receiveResponse);
		}
	}

}
