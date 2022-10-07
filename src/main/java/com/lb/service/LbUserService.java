package com.lb.service;

import com.lb.entity.LbUser;
import com.lb.vo.ActiveUser;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;

import javax.servlet.http.HttpSession;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName LbUserService.java
 * @Description TODO
 * @createTime 2020年03月25日 18:18:00
 */
public interface LbUserService {
    //校验登录
    ResponseResult checkUser(LbUser user, HttpSession session);

    /**
     * 注册用户
     */
    ResponseResult registUser(ActiveUser activeUser);

    //分页查询
    PageQuery<LbUser> findList(Integer pageNo, Integer pageSize, String username);

    /**
     * 保存
     */
    ResponseResult insertUser(LbUser lbUser);

    /**
     * 更新记录
     */
    ResponseResult updateUser(LbUser lbUser);

    /**
     * 根据主键id查询
     */
    LbUser findOne(Integer id);

    /**
     * 删除记录
     */
    ResponseResult deleteUser(Integer id);
}
