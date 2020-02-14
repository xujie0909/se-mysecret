package com.xujie.mysecret.dao;

import com.xujie.mysecret.entity.bill.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDao extends JpaRepository<Tag,Long> {
}
