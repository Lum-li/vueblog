package com.markerhub.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.markerhub.controller.entity.User;
import com.markerhub.service.UserService;
import com.markerhub.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;

//    这里看下token是不是我们JwtToken的类型是的话下面才可以强转
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    //    拿到用户后获取权限，拿到信息后封装成AuthorizationInfo返回shiro
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    /*进行身份验证，校验成功后返回基本信息*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken=(JwtToken) token;
        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        User user = userService.getById(Long.valueOf(userId));
        if(user==null){
            throw new UnknownAccountException(("账户不存在"));
        }
        if(user.getStatus()== -1){
            throw new LockedAccountException("账户锁定");
        }
        AccountProfile profile = new AccountProfile();
//        user -> profile
        BeanUtil.copyProperties(user,profile);


        return new SimpleAuthenticationInfo(profile,jwtToken.getCredentials(),getName());
    }
}
