package com.lb.dao;

import com.lb.entity.LbMedicalHistory;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface LbMedicalHistoryDao extends BaseMapper<LbMedicalHistory> {
    //
    void selectList(PageQuery<LbMedicalHistory> page);
}