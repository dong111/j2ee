package cn.dong111.book.chapter1.rmi.impl;

import cn.dong111.book.chapter1.rmi.Business;

import java.rmi.RemoteException;



/**
 * 描述：
 *
 * @author bluedavy
 * 创建时间： 2009-1-4
 */
public class BusinessImpl implements Business {

	/* (non-Javadoc)
	 * @see book.chapter1.rmi.Business#echo(java.lang.String)
	 */
	public String echo(String message) throws RemoteException {
		if("quit".equalsIgnoreCase(message.toString())){
			System.out.println("Server will be shutdown!");
			System.exit(0);
		}
		System.out.println("Message from client: "+message);
		return "Server response："+message;
	}

}
