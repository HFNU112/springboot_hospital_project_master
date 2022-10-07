package com.lb;

import com.lb.dao.LbPatientDao;
import com.lb.dao.LbUserDao;
import com.lb.entity.LbPatient;
import com.lb.entity.LbUser;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.query.LambdaQuery;
import org.beetl.sql.core.query.Query;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.security.RunAs;


@SpringBootTest
class LbUserTests {
    @Autowired
    private LbUserDao lbUserDao;
    @Autowired
    private LbPatientDao lbPatientDao;

    @Test
    public void userLoads() {
        lbUserDao.all().forEach(System.out::println);
    }

    //构造条件查询器
    @Test
    public void query() {
//        Query<LbUser> query = lbUserDao.createQuery();
//        query.andEq("username","admin1");
//        LbUser user = query.single("username");
        LbUser user = lbUserDao.findUserByUsername("admin1");
        System.out.println(user);
    }

    //构造lambda条件查询器
    @Test
    public void lambdaQuery() {
        LambdaQuery<LbUser> query = lbUserDao.createLambdaQuery();
        query.andEq(LbUser::getUsername,"hanmeimei");
        LbUser user = query.single();
        System.out.println(user);
    }

    //分页查询
    @Test
    public void queryPage() {
        PageQuery<LbUser> pageQuery = new PageQuery<>(1,10);
        lbUserDao.templatePage(pageQuery);
        pageQuery.getList().forEach(System.out::println);
    }

    //查询病人
    @Test
    public void queryPatient(){
        PageQuery pageQuery = new PageQuery(1,5);
        pageQuery.setPara("userId",5);
        lbPatientDao.templatePage(pageQuery);
        pageQuery.getList().forEach(System.out::println);
    }
}
