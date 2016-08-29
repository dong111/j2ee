package cn.dong111.baseBus.service;

import cn.dong111.baseBus.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author chendong
 * @version [版本号, 2016-08-29]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface IUserService
{

    public User getUserById(int userId);

}
