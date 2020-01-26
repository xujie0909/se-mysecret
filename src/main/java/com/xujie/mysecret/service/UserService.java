package com.xujie.mysecret.service;

import com.xujie.mysecret.entity.permission.User;

public interface UserService {

    String userToken(User user);

    User save(User user);

    String userInfo(String token);
}
