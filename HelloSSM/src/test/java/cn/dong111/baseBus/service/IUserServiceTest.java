package cn.dong111.baseBus.service;


import cn.dong111.baseBus.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author chendong
 * @version [版本号, 2016-08-29]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-mybatis.xml" })
public class IUserServiceTest {
    private static Logger logger = LoggerFactory.getLogger(IUserServiceTest.class);


    @Autowired
    private IUserService userService;

    @Test
    public void testGetUserById() throws Exception {
            long beginTime=System.nanoTime();
            User user = userService.getUserById(1);
            long endTime=System.nanoTime();
            System.out.println("查询时间 :" + (endTime-beginTime));
            logger.info("姓名："+user.getUserName());
    }
}