package com.xujie.mysecret.dao;

import com.xujie.mysecret.entity.bill.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDao extends  JpaRepository<Bill,Long>  {
}
