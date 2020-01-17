package com.xujie.mysecret.dao;

import com.xujie.mysecret.entity.permission.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
}
