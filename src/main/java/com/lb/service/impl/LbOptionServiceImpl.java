package com.lb.service.impl;

import com.lb.common.Global;
import com.lb.dao.LbOptionDao;
import com.lb.entity.LbDrugs;
import com.lb.entity.LbOption;
import com.lb.service.LbOptionService;
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
public class LbOptionServiceImpl implements LbOptionService {
    @Autowired
    private LbOptionDao lbOptionDao;

    @Override
    public PageQuery<LbOption> findList(Integer pageNo, Integer pageSize, String name, String type) {
        LambdaQuery<LbOption> query = lbOptionDao.createLambdaQuery();
        if (!StringUtils.isEmpty(name)) {
            query.andLike(LbOption::getName,name);
        }
        if (!StringUtils.isEmpty(type)) {
            query.andEq(LbOption::getType,type);
        }
        if (pageNo > 0 && pageSize > 0) {
            return query.desc(LbOption::getId).page(pageNo,pageSize);
        }
        return null;
    }

    @Override
    public List<LbOption> findAll() {
        return lbOptionDao.all();
    }

    @Override
    public ResponseResult insertOption(LbOption lbOption) {
        ResponseResult result = new ResponseResult();
        //先教验该医生的信息是否已经添加
        LambdaQuery<LbOption> query = lbOptionDao.createLambdaQuery();
        if (!StringUtils.isEmpty(lbOption.getName())) {
            query.andEq(LbOption::getName,lbOption.getName());
        }
        LbDrugs sysDrugs = query.single();
        if (sysDrugs != null) {
            result.setCode(Global.SAVE_CODE_ERROR);
            result.setMessage(Global.SAVE_MSG_DRUGS_ERROR);
        } else {
            lbOptionDao.insert(lbOption);
            result.setCode(Global.SAVE_CODE_SUCCESS);
            result.setMessage(Global.SAVE_MSG_SUCCESS);
        }
        return result;
    }

    @Override
    public ResponseResult updateOption(LbOption lbOption) {
        ResponseResult result = new ResponseResult();
        lbOptionDao.updateById(lbOption);
        result.setCode(Global.SAVE_CODE_SUCCESS);
        result.setMessage(Global.SAVE_MSG_SUCCESS);
        return result;
    }

    @Override
    public LbOption findOne(Integer id) {
        return lbOptionDao.single(id);
    }

    @Override
    public ResponseResult deleteOption(Integer id) {
        int rows = lbOptionDao.deleteById(id);
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
