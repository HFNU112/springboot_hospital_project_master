package com.lb.service.impl;

import com.lb.common.Global;
import com.lb.dao.LbAppointmentDao;
import com.lb.entity.LbAppointment;
import com.lb.service.LbAppointmentService;
import com.lb.vo.QueryVo;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName LbAppointmentServiceImpl.java
 * @Description TODO
 * @createTime 2020年03月27日 13:58:00
 */
@Service
public class LbAppointmentServiceImpl implements LbAppointmentService {
    @Autowired
    private LbAppointmentDao lbAppointmentDao;
    @Override
    public PageQuery<LbAppointment> findList(QueryVo queryVo) {
        PageQuery<LbAppointment> query = new PageQuery(queryVo.getPageNo(),queryVo.getPageSize());
        if (!StringUtils.isEmpty(queryVo.getPatientName())) {
            query.setPara("patientName",queryVo.getPatientName());
        }
        if (!StringUtils.isEmpty(queryVo.getDoctorName())) {
            query.setPara("doctorName",queryVo.getDoctorName());
        }
        if (queryVo.getUserId() != null) {
            query.setPara("userId",queryVo.getUserId());
        }
        query.setOrderBy("a.id desc");
        lbAppointmentDao.selectList(query);
        return query;
    }

    @Override
    public PageQuery<LbAppointment> findListByDoctor(QueryVo queryVo) {
        PageQuery<LbAppointment> query = new PageQuery(queryVo.getPageNo(),queryVo.getPageSize());
        if (queryVo.getUserId() != null) {
            query.setPara("userId",queryVo.getUserId());
        }
        if (!StringUtils.isEmpty(queryVo.getPatientName())) {
            query.setPara("patientName",queryVo.getPatientName());
        }
        if (!StringUtils.isEmpty(queryVo.getTime())) {
            query.setPara("time",queryVo.getTime());
        }
        query.setOrderBy("a.id desc");
        lbAppointmentDao.selectListByDoctor(query);
        return query;
    }

    @Override
    public ResponseResult insertAppointment(LbAppointment lbAppointment) {
        ResponseResult result = new ResponseResult();
        lbAppointmentDao.insert(lbAppointment);
        result.setCode(Global.SAVE_CODE_SUCCESS);
        result.setMessage(Global.SAVE_MSG_SUCCESS);
        return result;
    }

    @Override
    public ResponseResult updateAppointment(LbAppointment lbAppointment) {
        ResponseResult result = new ResponseResult();
        lbAppointmentDao.updateTemplateById(lbAppointment);
        result.setCode(Global.SAVE_CODE_SUCCESS);
        result.setMessage(Global.SAVE_MSG_SUCCESS);
        return result;
    }

    @Override
    public LbAppointment findOne(Integer id) {
        return lbAppointmentDao.selectOne(id);
    }

    @Override
    public ResponseResult deleteById(Integer id) {
        ResponseResult result = new ResponseResult();
        int rows = lbAppointmentDao.deleteById(id);
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
    public Integer insertReturnId(LbAppointment appointment) {
        return lbAppointmentDao.insertReturnKey(appointment).getInt();
    }
}
