package cn.dong111.book.chapter1.rmi;

import cn.dong111.book.chapter1.rmi.impl.BusinessImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;



/**
 * 描述：基于RMI实现的服务器端
 *
 * @author bluedavy
 * 创建时间： 2009-1-4
 */
public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		int port=9527;
		String name="BusinessDemo";
		Business business=new BusinessImpl();
		UnicastRemoteObject.exportObject(business, port);
		Registry registry=LocateRegistry.createRegistry(1099);
		registry.rebind(name, business);
	}

}
