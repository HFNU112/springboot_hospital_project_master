package com.lb.dao;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;
import com.lb.entity.*;
import org.springframework.stereotype.Repository;

/**
 * BaseMapper包含了内置的常用 CRUD
 */
// 通过@SqlResource 注解来指定Mapper对应的sql文件 user.md 文件，如果
//    不指定，则sql的md文件名称必须与接口名称一致（除去Dao后缀）
// @SqlResource("ron.user")  多级目录
//@SqlResource("LbUser")
@Repository
public interface LbUserDao extends BaseMapper<LbUser> {
    //根据用户名查询
    LbUser findUserByUsername(@Param("username") String username);
}