package cn.dong111.web.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author chendong
 * @version [版本号, 2016-09-06]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class HttpSessionMonitorListener implements HttpSessionListener {


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        System.out.println("----------------------------------sessionCreated");
        System.out.println("sessionId:                      " + session.getId());

        System.out.println("sessionCreationTime:            " + session.getCreationTime());

        System.out.println("sessionLastAccessedTime:        "+session.getLastAccessedTime());

        int maxInterval = session.getMaxInactiveInterval();
        System.out.println("sessionMaxInactiveInterval(s):  " + session.getMaxInactiveInterval());

        System.out.println("sessionExpirtion:               " + (session.getCreationTime() + maxInterval*1000)) ;


        System.out.println("----------------------------------sessionCreated");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        System.out.println("----------------------------------sessionDestroyed");
        System.out.println("sessionId:              " + session.getId());

        System.out.println("sessionCreationTime:    " + session.getCreationTime());

        System.out.println("sessionLastAccessedTime:"+session.getLastAccessedTime());

        System.out.println("hsName:                 "+session.getAttribute("hsName"));

        System.out.println("-----------------------------------sessionDestroyed");
    }
}
