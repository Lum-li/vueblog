package com.markerhub.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.markerhub.common.dto.LoginDto;
import com.markerhub.common.dto.Zhuce;
import com.markerhub.common.lang.Result;
import com.markerhub.controller.entity.User;
import com.markerhub.service.UserService;
import com.markerhub.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;



    @PostMapping("/login")
//    从RequestBody中获取参数
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){
        System.out.println("loginDto.getUsername"+loginDto.getUsername());
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");

        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
            return Result.fail("密码不正确");
        }
        String jwt = jwtUtils.generateToken(user.getId());
//        和JwtFilter里面的名字保持一直
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }
    @PostMapping("/Add")
    public void Add(@RequestBody Zhuce zhuce , HttpServletResponse response, HttpServletRequest request){

        System.out.println("进入Add方法成功");
        User user = new User();
        user.setUsername(zhuce.getUsername());
        user.setPassword(zhuce.getPassword());
        user.setEmail("731618705@qq.com");
         userService.saveBatch(user);

//        new User()User

        System.out.println("执行Add方法成功");

    }

//  @RequiresAuthentication权限的校验
//    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){
        System.out.println("进入方法成功");
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }
}
