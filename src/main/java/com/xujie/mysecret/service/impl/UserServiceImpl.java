package com.xujie.mysecret.service.impl;

import com.alibaba.fastjson.JSON;
import com.xujie.mysecret.cache.CacheContent;
import com.xujie.mysecret.cache.CacheManager;
import com.xujie.mysecret.dao.UserDao;
import com.xujie.mysecret.entity.permission.User;
import com.xujie.mysecret.service.UserService;
import com.xujie.mysecret.utils.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserDao userDao;


    private final CacheContent cache;

    public UserServiceImpl(UserDao userDao,CacheContent cache) {
        this.userDao = userDao;
        this.cache = cache;
    }

    @Override
    public String userToken(User user) {
        User rUser = userDao.findByUsername(user.getUsername());
        if(rUser == null){
            log.info("当前登录用户不存在！");
            return null;
        }

        //为暂时兼容页面，硬设置用户角色
        rUser.setRoles(new ArrayList<String>(){{
            add("admin");
        }});

        log.info("query result is:{}",rUser);
        if(rUser != null && rUser.getPassword().equals(user.getPassword())){
            rUser.setLoginStatus(User.loginStatus.SUCCESS.getLsCode());
            String token = SHA1.encode(user.getUsername());
            rUser.setToken(SHA1.encode(user.getUsername()));
            cache.save(token, JSON.toJSONString(rUser));
            return token;
        }
        return null;
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public String userInfo(String token) {
        String user = null;
        try {
            user = cache.get(token);
        } catch (Exception e) {
            log.error("can not get user info!and token is:{}",token,e);
        }
        return user;
    }


}
