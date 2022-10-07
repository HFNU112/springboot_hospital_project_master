package com.lb.controller.admin;

import com.lb.entity.LbHospitalization;
import com.lb.entity.LbPatient;
import com.lb.service.LbHospitalizationService;
import com.lb.service.LbPatientService;
import com.lb.utils.ExcelUtiles;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName PatientController.java
 * @Description 科目管理控制器
 * @createTime 2020年03月29日 12:48:00
 */
@Controller
@RequestMapping("/admin/hospitalization")
public class HospitalizationController {
    @Autowired
    private LbHospitalizationService lbHospitalizationService;
    @Autowired
    private LbPatientService lbPatientService;

    @ModelAttribute("patients")
    public List<LbPatient> getPatients() {
        return lbPatientService.findAll();
    }

    @RequestMapping("/manage")
    public String manage(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                         @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                         @RequestParam(required = false) String patientName,
                         @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date intime,
                         Model model) {
        //分页查询
        PageQuery<LbHospitalization> page = lbHospitalizationService.findList(pageNo,pageSize,patientName,intime);
        model.addAttribute("page",page);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("patientName",patientName);
        model.addAttribute("intime",intime);
        model.addAttribute("path","/admin/hospitalization/manage");
        return "admin/hospitalizationManage";
    }

    /**
     * 新增
     */
    @RequestMapping("/")
    public String addForm(LbHospitalization lbHospitalization,Model model) {
        model.addAttribute("hospitalization",lbHospitalization);
        return "admin/hospitalizationForm";
    }

    /**
     * 编辑
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("hospitalization",lbHospitalizationService.findOne(id));
        return "admin/hospitalizationForm";
    }

    /**
     * 异步插入记录
     */
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseResult insert(@RequestBody LbHospitalization lbHospitalization) {
        return lbHospitalizationService.insertHospitalization(lbHospitalization);
    }

    /**
     * 异步更新记录
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseResult update(@RequestBody LbHospitalization lbHospitalization) {
        return lbHospitalizationService.updateHospitalization(lbHospitalization);
    }

    /**
     * 异步删除
     */
    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseResult delete(@PathVariable Integer id){
        return lbHospitalizationService.deleteById(id);
    }

    /**
     * excel导出
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        List<LbHospitalization> list = lbHospitalizationService.findAll();
        ExcelUtiles.exportExcel(list,"住院记录","住院记录",LbHospitalization.class,"住院记录.xls",response);
    }
}
