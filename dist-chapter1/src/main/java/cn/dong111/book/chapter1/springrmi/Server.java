/**
 * 《构建高性能的大型分布式Java应用》
 *  书中的示例代码
 *  版权所有   2008---2009
 */
package cn.dong111.book.chapter1.springrmi;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 描述
 *
 * @author bluedavy 
 * 创建时间： 2009-2-11
 */
public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception, BeansException {
		new FileSystemXmlApplicationContext("cn/dong111/book/chapter1/springrmi/server.xml");
		System.out.println("Server has been started");
	}

}
