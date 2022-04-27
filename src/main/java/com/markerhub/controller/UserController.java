package com.markerhub.controller;


import com.markerhub.common.lang.Result;
import com.markerhub.controller.entity.User;
import com.markerhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2022-04-21
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired//这里的自动注入是怎么注入的？
    UserService userService;
    @GetMapping("/index")
//    这里做验证，直接返回了userid等于1的用户json
//    public Object index(){
//        return userService.getById(1L);
//    }
    public Object index(){
        User user = userService.getById(1L);

        return Result.succ(200,"操作成功",user);
    }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user) {
        return Result.succ(user);
    }

}
