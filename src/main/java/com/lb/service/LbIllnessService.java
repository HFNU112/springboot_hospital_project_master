package com.lb.service;

import com.lb.entity.LbIllness;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName LbDoctorService.java
 * @Description TODO
 * @createTime 2020年03月28日 13:59:00
 */
public interface LbIllnessService {
    //分页查询
    PageQuery<LbIllness> findList(Integer pageNo, Integer pageSize, String name);

    /**
     * 保存
     */
    ResponseResult insertIllness(LbIllness lbIllness);

    /**
     * 更新记录
     */
    ResponseResult updateIllness(LbIllness lbIllness);

    /**
     * 根据主键id查询
     */
    LbIllness findOne(Integer id);

    /**
     * 删除记录
     */
    ResponseResult deleteIllness(Integer id);
}
