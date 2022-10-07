package com.lb.dao;
import org.beetl.sql.core.annotatoin.*;
import org.beetl.sql.core.db.KeyHolder;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import com.lb.entity.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LbOptionDao extends BaseMapper<LbOption> {
    //根据检查项，计算费用
    BigDecimal getTotalPrice(@Param("ids") List<Integer> ids);
}