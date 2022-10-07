package com.lb.service.impl;

import com.lb.common.Global;
import com.lb.dao.LbAppointmentDao;
import com.lb.dao.LbOptionDao;
import com.lb.dao.LbSeekDao;
import com.lb.entity.LbAppointment;
import com.lb.entity.LbSeek;
import com.lb.service.LbSeekService;
import com.lb.utils.DrugsUtils;
import com.lb.utils.OptionUtils;
import com.lb.vo.QueryVo;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.db.KeyHolder;
import org.beetl.sql.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ClassName： LbSeekServiceImpl
 * @Description：
 * @Author: 蓝莲花
 * @Date： 2020/4/5 下午1:48
 * @Version： V1.0
 **/
@Service
public class LbSeekServiceImpl implements LbSeekService {
    @Autowired
    private LbSeekDao lbSeekDao;
    @Autowired
    private LbOptionDao lbOptionDao;
    @Autowired
    private LbAppointmentDao lbAppointmentDao;

    @Override
    public ResponseResult save(Map map, HttpSession session) {
        //每个预约只能生成一条就诊记录
        Integer appointmentId = Integer.valueOf(String.valueOf(map.get("appointmentId")));
        Query<LbSeek> query = lbSeekDao.createQuery();
        query.andEq("appointment_id",appointmentId);
        LbSeek seek = query.single();
        if (seek == null){
            seek = new LbSeek();
            seek.setPatientId(Integer.valueOf(String.valueOf(map.get("patientId"))));
            seek.setDays(Integer.valueOf(String.valueOf(map.get("days"))));
            seek.setDescribes(String.valueOf(map.get("describes")));
            seek.setIllname(String.valueOf(map.get("illname")));
            seek.setOptions(OptionUtils.getOptionIds(map));
            seek.setAppointmentId(Integer.valueOf(String.valueOf(map.get("appointmentId"))));
            //根据检查项，计算出所需的费用
            seek.setPrice(lbOptionDao.getTotalPrice(OptionUtils.getOptionIds(seek.getOptions())));
            KeyHolder keyHolder = lbSeekDao.insertReturnKey(seek);

            //修改预约记录状态
            LbAppointment appointment = new LbAppointment();
            appointment.setId(Integer.valueOf(String.valueOf(map.get("appointmentId"))));
            appointment.setStatus(Global.SEEK_CODE_PROCESSING);
            lbAppointmentDao.upsertByTemplate(appointment);

            //将新记录的id存储到session中
            session.setAttribute("seek_" + map.get("patientId"),keyHolder.getInt());
            return new ResponseResult(Global.SAVE_CODE_SUCCESS,Global.SAVE_MSG_SUCCESS);
        } else {
            return new ResponseResult(Global.SAVE_MSG_ERROR,Global.SEEK_MSG_EXIST);
        }

    }

    @Override
    public ResponseResult update(Map map,HttpSession session) {
        Integer seekId = (Integer) session.getAttribute("seek_" + map.get("patientId"));
        LbSeek seek = new LbSeek();
        seek.setId(seekId);
        seek.setDrugs(DrugsUtils.getDrugsInfo(map));
        lbSeekDao.upsertByTemplate(seek);
        return new ResponseResult(Global.SAVE_CODE_SUCCESS,Global.SAVE_MSG_SUCCESS);
    }

    @Override
    public LbSeek findOneByPatientId(Integer patientId,Integer appointmentId,HttpSession session) {
        LbSeek seek = null;
        Integer seekId = (Integer) session.getAttribute("seek_" + patientId);
        if (seekId!=null){//如果当前回话中插入就诊记录，可以从session中获取就诊id
            seek = lbSeekDao.single(seekId);
        }else { //反之，就诊记录已经存在，根据预约编号查询
            Query<LbSeek> query =lbSeekDao.createQuery();
            query.andEq("appointment_id",appointmentId);
            seek = query.single();
        }
        return seek;
    }
}
