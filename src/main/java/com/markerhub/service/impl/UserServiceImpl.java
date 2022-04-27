package com.markerhub.service.impl;

import com.markerhub.controller.entity.User;
import com.markerhub.mapper.UserMapper;
import com.markerhub.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2022-04-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public void saveBatch(User user) {
        userMapper.insertUser(user);
    }
}
