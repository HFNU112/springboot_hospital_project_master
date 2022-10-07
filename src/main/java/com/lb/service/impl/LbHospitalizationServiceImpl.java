package com.lb.service.impl;

import com.lb.common.Global;
import com.lb.dao.LbHospitalizationDao;
import com.lb.entity.LbHospitalization;
import com.lb.service.LbHospitalizationService;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName LbHospitalizationServiceImpl.java
 * @Description TODO
 * @createTime 2020年03月27日 13:58:00
 */
@Service
public class LbHospitalizationServiceImpl implements LbHospitalizationService {
    @Autowired
    private LbHospitalizationDao lbHospitalizationDao;
    @Override
    public PageQuery<LbHospitalization> findList(long pageNo, long pageSize, String patientName, Date intime) {
        PageQuery<LbHospitalization> query = new PageQuery(pageNo,pageSize);
        if (!StringUtils.isEmpty(patientName)) {
            query.setPara("patientName",patientName);
        }
        if (intime != null) {
            query.setPara("intime",intime);
        }
        query.setOrderBy("h.id desc");
        lbHospitalizationDao.selectList(query);
        return query;
    }

    @Override
    public List<LbHospitalization> findAll() {
        return lbHospitalizationDao.selectAll();
    }

    @Override
    public ResponseResult insertHospitalization(LbHospitalization lbHospitalization) {
        ResponseResult result = new ResponseResult();
        lbHospitalizationDao.insert(lbHospitalization);
        result.setCode(Global.SAVE_CODE_SUCCESS);
        result.setMessage(Global.SAVE_MSG_SUCCESS);
        result.setData(String.valueOf(lbHospitalizationDao.insertReturnKey(lbHospitalization).getInt()));
        return result;
    }

    @Override
    public ResponseResult updateHospitalization(LbHospitalization lbHospitalization) {
        ResponseResult result = new ResponseResult();
        lbHospitalizationDao.updateById(lbHospitalization);
        result.setCode(Global.SAVE_CODE_SUCCESS);
        result.setMessage(Global.SAVE_MSG_SUCCESS);
        return result;
    }

    @Override
    public LbHospitalization findOne(Integer id) {
        return lbHospitalizationDao.single(id);
    }

    @Override
    public ResponseResult deleteById(Integer id) {
        ResponseResult result = new ResponseResult();

        int rows = lbHospitalizationDao.deleteById(id);
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
    public LbHospitalization findOneByUserId(Integer userId) {
        return lbHospitalizationDao.findOneByUserId(userId);
    }
}
