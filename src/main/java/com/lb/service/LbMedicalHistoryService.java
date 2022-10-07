package com.lb.service;

import com.lb.entity.LbMedicalHistory;
import com.lb.vo.QueryVo;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName LbMedicalHistoryService.java
 * @Description TODO
 * @createTime 2020年03月27日 13:59:00
 */
public interface LbMedicalHistoryService {
    //查集合
    PageQuery<LbMedicalHistory> findList(QueryVo queryVo);

    /**
     * 保存
     */
    ResponseResult insertMedicalHistory(LbMedicalHistory lbMedicalHistory);

    /**
     * 更新记录
     */
    ResponseResult updateMedicalHistory(LbMedicalHistory lbMedicalHistory);

    /**
     * 根据主键id查询
     */
    LbMedicalHistory findOne(Integer id);

    /**
     * 删除
     */
    ResponseResult deleteById(Integer id);
}
