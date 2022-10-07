package com.lb.dao;
import org.beetl.sql.core.annotatoin.*;
import org.beetl.sql.core.db.KeyHolder;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import com.lb.entity.*;
import org.springframework.stereotype.Repository;

@Repository
public interface LbDrugsDao extends BaseMapper<LbDrugs> {
    //
}