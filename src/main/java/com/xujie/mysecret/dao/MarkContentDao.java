package com.xujie.mysecret.dao;

import com.xujie.mysecret.entity.MarkContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xujie17
 */
@Repository
public interface MarkContentDao extends JpaRepository<MarkContent, Long> {
}
