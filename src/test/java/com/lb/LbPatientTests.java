package com.lb;

import com.lb.dao.LbPatientDao;
import com.lb.entity.LbPatient;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.query.Query;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class LbPatientTests {
    @Autowired
    private LbPatientDao lbPatientDao;


    //构造条件查询器
    @Test
    public void query() {
        Query<LbPatient> query = lbPatientDao.createQuery();
        query.appendSql("p left join lb_appointment a on p.appointment_id=a.id\n" +
                "    left join lb_doctor d on d.id=a.doctor_id ");
        PageQuery<LbPatient> page = query.orderBy("p.id desc").page(1,5);
        page.getList().forEach(System.out::println);
    }

    @Test
    public void customQuery() {
        PageQuery<LbPatient> query = new PageQuery(1,5);
        query.setPara("name","郝一");
        lbPatientDao.selectList(query);
        query.getList().forEach(System.out::println);
    }

}
