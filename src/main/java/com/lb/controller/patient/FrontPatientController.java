package com.lb.controller.patient;

import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.lb.common.Global;
import com.lb.entity.LbAppointment;
import com.lb.entity.LbMedicalHistory;
import com.lb.entity.LbPatient;
import com.lb.entity.LbUser;
import com.lb.service.LbAppointmentService;
import com.lb.service.LbHospitalizationService;
import com.lb.service.LbMedicalHistoryService;
import com.lb.service.LbPatientService;
import com.lb.utils.DateUtils;
import com.lb.utils.PDFUtils;
import com.lb.vo.QueryVo;
import com.lb.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName PatientController.java
 * @Description 患者控制器
 * @createTime 2020年03月29日 20:54:00
 */
@Slf4j
@Controller
@RequestMapping("/patient")
public class FrontPatientController {
    @Value("${filepath.appointpdf}")
    private String appointPath;

    @Autowired
    private LbAppointmentService lbAppointmentService;
    @Autowired
    private LbPatientService lbPatientService;
    @Autowired
    private LbMedicalHistoryService lbMedicalHistoryService;
    @Autowired
    private LbHospitalizationService lbHospitalizationService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 患者主页面，显示当前患者的预约记录
     */
    @RequestMapping("/index")
    public String index(HttpSession session,QueryVo queryVo, Model model) {
        //查询当前登录用户的预约记录
        LbUser user = (LbUser) session.getAttribute("user");
        queryVo.setUserId(user.getId());
        PageQuery<LbAppointment> page = lbAppointmentService.findList(queryVo);
        model.addAttribute("page",page);
        model.addAttribute("pageNo",queryVo.getPageNo());
        model.addAttribute("path","/patient/index");
        return "patient/appointmentHistory";
    }

    /**
     * 预约表单
     */
    @RequestMapping(value = "/appointment", method = RequestMethod.GET)
    public String form(HttpSession session,Model model) {
        LbUser user = (LbUser) session.getAttribute("user");
        LbPatient patient = lbPatientService.findOneByUserId(user.getId());
        //将患者的信息放到model
        model.addAttribute("patient",patient);
        return "patient/appointmentForm";
    }

    /**
     * 预约保存
     */
    @ResponseBody
    @RequestMapping(value = "/appointment", method = RequestMethod.POST)
    public ResponseResult save(@RequestBody LbAppointment appointment) {
        appointment.setStatus(Global.SEEK_CODE_NONE);
        Integer appointmentId = lbAppointmentService.insertReturnId(appointment);
        LbPatient patient = new LbPatient();
        patient.setId(appointment.getPatientId());
        patient.setAppointmentId(appointmentId);
        lbPatientService.updatePatient(patient);
        return new ResponseResult(Global.SAVE_CODE_SUCCESS,Global.SAVE_APPOINTMENT_SUCCESS);
    }

    /**
     * 生成挂号单
     */
    @ResponseBody
    @RequestMapping(value = "/appointment/createPDF",method = RequestMethod.POST)
    public ResponseResult createPDF(HttpSession session) {
        //获取当前用户最近一次的预约记录
        LbUser user = (LbUser) session.getAttribute("user");
        LbPatient patient = lbPatientService.findOneByUserId(user.getId());
        LbAppointment appointment = lbAppointmentService.findOne(patient.getAppointmentId());
        return new ResponseResult(Global.APPOINTMENT_CODE_SUCCESS, PDFUtils.createAppointment(appointment,appointPath));
    }

    @RequestMapping(value = "/appointment/downPDF",method = RequestMethod.GET)
    public void downPDF(HttpSession session, HttpServletResponse response) {
        //获取当前用户最近一次的预约记录
        LbUser user = (LbUser) session.getAttribute("user");
        LbPatient patient = lbPatientService.findOneByUserId(user.getId());
        LbAppointment appointment = lbAppointmentService.findOne(patient.getAppointmentId());
        String fileName = appointment.getPatientName()+ DateUtils.date2String(new Date())+"挂号单.pdf";
        String filePath = appointPath+fileName;
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                response.sendError(404, "File not found!");
            } catch (IOException e) {
                logger.error("文件不存在",e);
            }
        }
        try {
            fileName = URLEncoder.encode(fileName, "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        InputStream in = null;
        OutputStream out = null;
        try {

            //获取要下载的文件输入流
            in = new FileInputStream(filePath);
            int len = 0;
            //创建数据缓冲区
            byte[] buffer = new byte[1024];
            //通过response对象获取outputStream流
            out = response.getOutputStream();
            //将FileInputStream流写入到buffer缓冲区
            while((len = in.read(buffer)) > 0) {
                out.write(buffer,0,len);
            }
        } catch (Exception e) {
            logger.error("下载pdf失败",e);
        } finally {
            try {
                if (out != null)
                    out.close();
                if(in!=null)
                    in.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
    /**
     * 查询
     */
    @RequestMapping("/search")
    public String search() {
        return "patient/search";
    }

    /**
     * 查询信息
     */
    @ResponseBody
    @RequestMapping("/searchInfo")
    public Map<String, List> searchInfo(String type,String name) {
        return lbPatientService.findInfo(type,name);
    }

    /**
     * 查看既往病史
     */
    @RequestMapping("/medicalHistory")
    public String medicalHistory(QueryVo queryVo,HttpSession session, Model model) {
        LbUser user = (LbUser) session.getAttribute("user");
        queryVo.setUserId(user.getId());
        PageQuery<LbMedicalHistory> page = lbMedicalHistoryService.findList(queryVo);
        model.addAttribute("page",page);
        model.addAttribute("pageNo",queryVo.getPageNo());
        model.addAttribute("path","/patient/medicalHistory");
        return "patient/medicalHistory";
    }

    /**
     * 查询最近一次住院记录
     */
    @RequestMapping("/hospitalization")
    public String hospitalization(HttpSession session, Model model) {
        LbUser user = (LbUser) session.getAttribute("user");
        model.addAttribute("hospitalization",lbHospitalizationService.findOneByUserId(user.getId()));
        return "patient/hospitalization";
    }
}
