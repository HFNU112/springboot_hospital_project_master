package com.lb.dao;
import com.lb.entity.LbHospitalization;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LbHospitalizationDao extends BaseMapper<LbHospitalization> {
    //查询住院信息关联查询患者信息
    void selectList(PageQuery<LbHospitalization> page);

    //查询所有的住院记录
    List<LbHospitalization> selectAll();

    //根据userid查询住院信息
    LbHospitalization findOneByUserId(@Param("userId") Integer userId);
}