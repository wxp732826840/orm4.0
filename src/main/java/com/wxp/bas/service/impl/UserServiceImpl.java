package com.wxp.bas.service.impl;

import com.wxp.bas.entity.User;
import com.wxp.bas.mapper.UserMapper;
import com.wxp.bas.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wxp
 * @since 2019-12-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
