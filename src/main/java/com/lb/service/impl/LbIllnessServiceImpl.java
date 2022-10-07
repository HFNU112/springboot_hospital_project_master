package com.lb.service.impl;

import com.lb.common.Global;
import com.lb.dao.LbIllnessDao;
import com.lb.entity.LbDrugs;
import com.lb.entity.LbIllness;
import com.lb.service.LbIllnessService;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.query.LambdaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName LbDoctorServiceImpl.java
 * @Description TODO
 * @createTime 2020年03月26日 14:00:00
 */
@Service
public class LbIllnessServiceImpl implements LbIllnessService {
    @Autowired
    private LbIllnessDao lbIllnessDao;

    @Override
    public PageQuery<LbIllness> findList(Integer pageNo, Integer pageSize, String name) {
        LambdaQuery<LbIllness> query = lbIllnessDao.createLambdaQuery();
        if (!StringUtils.isEmpty(name)) {
            query.andLike(LbIllness::getName,name);
        }
        if (pageNo > 0 && pageSize > 0) {
            return query.desc(LbIllness::getId).page(pageNo,pageSize);
        }
        return null;
    }

    @Override
    public ResponseResult insertIllness(LbIllness lbDrugs) {
        ResponseResult result = new ResponseResult();
        //先教验该医生的信息是否已经添加
        LambdaQuery<LbIllness> query = lbIllnessDao.createLambdaQuery();
        if (!StringUtils.isEmpty(lbDrugs.getName())) {
            query.andEq(LbIllness::getName,lbDrugs.getName());
        }
        LbDrugs sysDrugs = query.single();
        if (sysDrugs != null) {
            result.setCode(Global.SAVE_CODE_ERROR);
            result.setMessage(Global.SAVE_MSG_DRUGS_ERROR);
        } else {
            lbIllnessDao.insert(lbDrugs);
            result.setCode(Global.SAVE_CODE_SUCCESS);
            result.setMessage(Global.SAVE_MSG_SUCCESS);
        }
        return result;
    }

    @Override
    public ResponseResult updateIllness(LbIllness lbDrugs) {
        ResponseResult result = new ResponseResult();
        lbIllnessDao.updateById(lbDrugs);
        result.setCode(Global.SAVE_CODE_SUCCESS);
        result.setMessage(Global.SAVE_MSG_SUCCESS);
        return result;
    }

    @Override
    public LbIllness findOne(Integer id) {
        return lbIllnessDao.single(id);
    }

    @Override
    public ResponseResult deleteIllness(Integer id) {
        int rows = lbIllnessDao.deleteById(id);
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
