package com.lb.service.impl;

import com.lb.common.Global;
import com.lb.dao.LbDrugsDao;
import com.lb.entity.LbDrugs;
import com.lb.service.LbDrugsService;
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
public class LbDrugsServiceImpl implements LbDrugsService {
    @Autowired
    private LbDrugsDao lbDrugsDao;

    @Override
    public PageQuery<LbDrugs> findList(Integer pageNo, Integer pageSize, String name, String type) {
        LambdaQuery<LbDrugs> query = lbDrugsDao.createLambdaQuery();
        if (!StringUtils.isEmpty(name)) {
            query.andLike(LbDrugs::getName,name);
        }
        if (!StringUtils.isEmpty(type)) {
            query.andEq(LbDrugs::getType,type);
        }
        if (pageNo > 0 && pageSize > 0) {
            return query.desc(LbDrugs::getId).page(pageNo,pageSize);
        }
        return null;
    }

    @Override
    public List<LbDrugs> findAll() {
        return lbDrugsDao.all();
    }

    @Override
    public ResponseResult insertDrugs(LbDrugs lbDrugs) {
        ResponseResult result = new ResponseResult();
        //先教验该医生的信息是否已经添加
        LambdaQuery<LbDrugs> query = lbDrugsDao.createLambdaQuery();
        if (!StringUtils.isEmpty(lbDrugs.getName())) {
            query.andEq(LbDrugs::getName,lbDrugs.getName());
        }
        LbDrugs sysDrugs = query.single();
        if (sysDrugs != null) {
            result.setCode(Global.SAVE_CODE_ERROR);
            result.setMessage(Global.SAVE_MSG_DRUGS_ERROR);
        } else {
            lbDrugsDao.insert(lbDrugs);
            result.setCode(Global.SAVE_CODE_SUCCESS);
            result.setMessage(Global.SAVE_MSG_SUCCESS);
        }
        return result;
    }

    @Override
    public ResponseResult updateDrugs(LbDrugs lbDrugs) {
        ResponseResult result = new ResponseResult();
        lbDrugsDao.updateById(lbDrugs);
        result.setCode(Global.SAVE_CODE_SUCCESS);
        result.setMessage(Global.SAVE_MSG_SUCCESS);
        return result;
    }

    @Override
    public LbDrugs findOne(Integer id) {
        return lbDrugsDao.single(id);
    }

    @Override
    public ResponseResult deleteDrugs(Integer id) {
        int rows = lbDrugsDao.deleteById(id);
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
}
