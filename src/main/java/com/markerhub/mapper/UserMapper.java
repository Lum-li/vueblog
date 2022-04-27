package com.markerhub.mapper;

import com.markerhub.controller.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2022-04-21
 */
public interface UserMapper extends BaseMapper<User> {

    void insertUser(@Param("user") User user);
}
