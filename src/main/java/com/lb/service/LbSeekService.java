package com.lb.service;

import com.lb.entity.LbSeek;
import com.lb.vo.QueryVo;
import com.lb.vo.ResponseResult;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ClassName： LbSeekService
 * @Description：
 * @Author: 蓝莲花
 * @Date： 2020/4/5 下午1:48
 * @Version： V1.0
 **/
public interface LbSeekService {
    //保存记录
    ResponseResult save(Map map, HttpSession session);

    //更新记录
    ResponseResult update(Map map, HttpSession session);

    //获取一条记录
    LbSeek findOneByPatientId(Integer patientId,Integer appointmentId,HttpSession session);
}
