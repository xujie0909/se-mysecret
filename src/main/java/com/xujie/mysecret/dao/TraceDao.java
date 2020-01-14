package com.xujie.mysecret.dao;

import com.xujie.mysecret.entity.Trace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraceDao extends JpaRepository<Trace, Long> {

}
