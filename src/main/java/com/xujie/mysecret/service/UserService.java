package com.xujie.mysecret.service;

import com.xujie.mysecret.entity.permission.User;

public interface UserService {

    void findByUsername(String username);

    void save(User user);
}
