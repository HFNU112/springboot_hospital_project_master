package com.lb.service.impl;

import com.lb.common.Global;
import com.lb.dao.LbDoctorDao;
import com.lb.entity.LbDoctor;
import com.lb.service.LbDoctorService;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.query.LambdaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName LbDoctorServiceImpl.java
 * @Description TODO
 * @createTime 2020年03月26日 14:00:00
 */
@Service
public class LbDoctorServiceImpl implements LbDoctorService {
    @Autowired
    private LbDoctorDao lbDoctorDao;

    @Override
    public PageQuery<LbDoctor> findList(Integer pageNo, Integer pageSize,String name,String certId) {
        LambdaQuery<LbDoctor> query = lbDoctorDao.createLambdaQuery();
        if (!StringUtils.isEmpty(name)) {
            query.andLike(LbDoctor::getName,name);
        }
        if (!StringUtils.isEmpty(certId)) {
            query.andEq(LbDoctor::getCertId,certId);
        }
        if (pageNo > 0 && pageSize > 0) {
            return query.desc(LbDoctor::getId).page(pageNo,pageSize);
        }
        return null;
    }

    @Override
    public List<LbDoctor> findAll() {
        return lbDoctorDao.all();
    }

    @Override
    public ResponseResult insertDoctor(LbDoctor lbDoctor) {
        ResponseResult result = new ResponseResult();
        //先教验该医生的信息是否已经添加
        LambdaQuery<LbDoctor> query = lbDoctorDao.createLambdaQuery();
        if (!StringUtils.isEmpty(lbDoctor.getCertId())) {
            query.andEq(LbDoctor::getCertId,lbDoctor.getCertId());
        }
        LbDoctor sysDoctor = query.single();
        if (sysDoctor != null) {
            result.setCode(Global.SAVE_CODE_ERROR);
            result.setMessage(Global.SAVE_MSG_ERROR);
        } else {
            lbDoctorDao.insert(lbDoctor);
            result.setCode(Global.SAVE_CODE_SUCCESS);
            result.setMessage(Global.SAVE_MSG_SUCCESS);
        }
        return result;
    }

    @Override
    public ResponseResult updateDoctor(LbDoctor lbDoctor) {
        ResponseResult result = new ResponseResult();
        lbDoctorDao.updateById(lbDoctor);
        result.setCode(Global.SAVE_CODE_SUCCESS);
        result.setMessage(Global.SAVE_MSG_SUCCESS);
        return result;
    }

    @Override
    public LbDoctor findOne(Integer id) {
        return lbDoctorDao.single(id);
    }

    @Override
    public ResponseResult deleteDoctor(Integer id) {
        int rows = lbDoctorDao.deleteById(id);
        ResponseResult result = new ResponseResult();
        if (rows > 0) {
            result.setCode(Global.DEL_CODE_SUCCESS);
            result.setMessage(Global.DEL_MSG_SUCCESS);
        } else {
            result.setCode(Global.DEL_CODE_ERROR);
            result.setMessage(Global.DEL_MSG_ERROR);
        }
        return result;
    }

    @Override
    public List<LbDoctor> getListByDepartment(String department) {
        LambdaQuery<LbDoctor> query = lbDoctorDao.createLambdaQuery();
        query.andEq(LbDoctor::getDepartment,department);
        return query.select();
    }

    @Override
    public LbDoctor findOneByUserId(Integer userId) {
        LambdaQuery<LbDoctor> query = lbDoctorDao.createLambdaQuery();
        query.andEq(LbDoctor::getUserId,userId);
        return query.single();
    }
}
