package com.xujie.mysecret.dao.mapper;

import com.xujie.mysecret.entity.bill.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BillMapper {

    /**
     * 查询账单列表
     * @param bill 查询条件
     * @return 返回数据
     */
    List<Bill> findByBill(@Param("bill") Bill bill);

    /**
     * 账单来源占比
     * @return 数据
     */
    List<Map<String,Object>> sourceProportion();
}
