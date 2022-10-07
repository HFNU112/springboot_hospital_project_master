package com.lb.controller.doctor;

import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.lb.common.Global;
import com.lb.entity.*;
import com.lb.service.*;
import com.lb.utils.DateUtils;
import com.lb.utils.PDFUtils;
import com.lb.vo.QueryVo;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
 * @ClassName DoctorController.java
 * @Description 医生管理端控制台
 * @createTime 2020年03月26日 13:57:00
 */
@Controller
@RequestMapping("/doctor")
public class FrontDoctorController {

    @Value("${filepath.appointpdf}")
    private String appointPath;
    @Autowired
    private LbDoctorService lbDoctorService;
    @Autowired
    private LbPatientService lbPatientService;
    @Autowired
    private LbAppointmentService lbAppointmentService;
    @Autowired
    private LbOptionService lbOptionService;
    @Autowired
    private LbDrugsService lbDrugsService;
    @Autowired
    private LbSeekService lbSeekService;
    @Autowired
    private LbHospitalizationService lbHospitalizationService;
    @Autowired
    private LbMedicalHistoryService lbMedicalHistoryService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${filepath.seekpdfpath}")
    private String path;

    /**
     * 医生控制台
     */
    @RequestMapping("/index")
    public String index(QueryVo queryVo, HttpSession session, Model model){
        LbUser user = (LbUser) session.getAttribute("user");
        queryVo.setUserId(user.getId());
        LbDoctor doctor = lbDoctorService.findOneByUserId(user.getId());

        //获取患者信息
        PageQuery<LbAppointment> page = lbAppointmentService.findListByDoctor(queryVo);
        model.addAttribute("page", page);
        model.addAttribute("pageNo",queryVo.getPageNo());
        model.addAttribute("path","/doctor/index");
        model.addAttribute("doctor", doctor);
        return "doctor/index";
    }

    /**
     * 就医
     */
    @RequestMapping("/seek/{id}/{appointmentId}")
    public String seek(@PathVariable("id") Integer patientId,
                       @PathVariable("appointmentId") Integer appointmentId,
                       Model model){
        //获取患者信息
        model.addAttribute("patient",lbPatientService.findOne(patientId));
        //检查项目列表
        model.addAttribute("options",lbOptionService.findAll());
        //药品列表
        model.addAttribute("drugs", lbDrugsService.findAll());
        model.addAttribute("appointmentId", appointmentId);
        return "doctor/seek";
    }

    /**
     * 就诊
     */
    @ResponseBody
    @RequestMapping("/seekInfo")
    public ResponseResult seekInfo(@RequestBody Map map,HttpSession session) {
        return lbSeekService.save(map,session);
    }

    /**
     * 开药
     */
    @ResponseBody
    @RequestMapping("/drug")
    public ResponseResult drug(@RequestBody Map map,HttpSession session) {
        return lbSeekService.update(map,session);
    }

    /**
     * 住院
     */
    @ResponseBody
    @RequestMapping("/zation")
    public ResponseResult zation(@RequestBody LbHospitalization hospitalization) {
        ResponseResult result = lbHospitalizationService.insertHospitalization(hospitalization);
        return result;
    }

    /**
     * 查看患者病史
     */
    @RequestMapping("/medicalHistory/{id}")
    public String medicalHistory(@PathVariable("id") Integer patientId,Model model) {
        QueryVo queryVo = new QueryVo();
        queryVo.setPatientId(patientId);
        List<LbMedicalHistory> medicalHistoryList = lbMedicalHistoryService.findList(queryVo).getList();
        model.addAttribute("medicalHistoryList",medicalHistoryList);
        return "doctor/medicalHistory";
    }

    /**
     * @param session
     * @return
     */
    @RequestMapping( value = "/printSeek/{id}//{appointmentId}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult printSeek(@PathVariable("id") Integer patientId,
                                    @PathVariable("appointmentId") Integer appointmentId,
                                    HttpSession session){
        LbUser user = (LbUser) session.getAttribute("user");
        LbDoctor doctor = lbDoctorService.findOneByUserId(user.getId());
        LbSeek seek = lbSeekService.findOneByPatientId(patientId,appointmentId,session);
        seek.setPatientName(lbPatientService.findOne(patientId).getName());
        seek.setDoctorName(doctor.getName());
        //createSeekInfo，第三个参数填空字符串就是生成在项目根目录里面，要是想生成在别的路径，例：D:\\ 就是生成在D盘根目录
        String message = PDFUtils.createSeekInfo(seek,lbOptionService,path);
        return new ResponseResult(Global.SEEK_CODE_SUCCESS,message);
    }

    /**
     * 就诊完成
     */
    @ResponseBody
    @RequestMapping("/finishSeek/{id}")
    public ResponseResult finishSeek(@PathVariable Integer id) {
        LbAppointment appointment = new LbAppointment();
        appointment.setId(id);
        appointment.setStatus(Global.SEEK_CODE_DONE);
        return lbAppointmentService.updateAppointment(appointment);
    }



    @RequestMapping(value = "/printSeek/downPDF",method = RequestMethod.GET)
    public void downPDF(Integer patientId,
                        Integer appointmentId,HttpSession session, HttpServletResponse response) {
        //获取当前用户最近一次的预约记录
        LbSeek seek = lbSeekService.findOneByPatientId(patientId,appointmentId,session);
        seek.setPatientName(lbPatientService.findOne(patientId).getName());
        String fileName = seek.getPatientName()+DateUtils.date2String(new Date())+"就诊单.pdf";
        String filePath = path+fileName;
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
     * 根据部门查询医生
     */
    @ResponseBody
    @RequestMapping(value = "/getList/{department}")
    public List<LbDoctor> getList(@PathVariable String department){
        return lbDoctorService.getListByDepartment(department);
    }
}
