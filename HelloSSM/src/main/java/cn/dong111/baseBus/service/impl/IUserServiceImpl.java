package cn.dong111.baseBus.service.impl;

import cn.dong111.baseBus.entity.User;
import cn.dong111.baseBus.mapper.UserMapper;
import cn.dong111.baseBus.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chendong
 * @version [版本号, 2016-08-29]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class IUserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(int userId) {
        return this.userMapper.selectByPrimaryKey(userId);
    }
}
