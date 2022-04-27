package com.markerhub.service;

import com.markerhub.controller.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2022-04-21
 */
public interface UserService extends IService<User> {

    void saveBatch(User user);
}
