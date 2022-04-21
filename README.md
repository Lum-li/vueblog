# Shiro整合jwt
用户发送用户名密码给后端，后端校验后生成jwt返回给用户
用户拿着jwt去访问资源(去handle里面拿也就是controler)
    进行jwtfilter
        需要jwt
            shiro登录处理（获取用户id）
                jwt有效
                    @requireroles（shiro注解）需要角色
                        有角色通过
                        无角色 异常
                jwt无效：过期，不对——异常处理

        无须jwt
            @requireroles（shiro注解）需要角色
                有角色通过
                无角色异常


# 延伸阅读

