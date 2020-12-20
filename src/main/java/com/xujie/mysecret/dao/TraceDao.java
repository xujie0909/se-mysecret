package com.xujie.mysecret.dao;

import com.xujie.mysecret.entity.mark.Trace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xujie
 */
@Repository
public interface TraceDao extends JpaRepository<Trace, Long> {

}
