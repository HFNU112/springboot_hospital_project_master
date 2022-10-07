package com.lb.controller.admin;

import com.lb.entity.LbDoctor;
import com.lb.service.LbDoctorService;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName DoctorController.java
 * @Description 医生的后台控制台
 * @createTime 2020年03月26日 13:57:00
 */
@Controller
@RequestMapping("/admin/doctor")
public class DoctorController {
    @Autowired
    private LbDoctorService lbDoctorService;

    @RequestMapping("/manage")
    public String doctorManage(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                               @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String certId,
                               Model model) {
        //查询医生的集合数据
        PageQuery<LbDoctor> page = lbDoctorService.findList(pageNo,pageSize,name,certId);
        model.addAttribute("page",page);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("name",name);
        model.addAttribute("certId",certId);
        model.addAttribute("path","/admin/doctor/manage");
        return "admin/doctorManage";
    }

    /**
     * 医生新增
     */
    @RequestMapping("/")
    public String doctorAddForm(LbDoctor lbDoctor,Model model) {
        model.addAttribute("doctor",lbDoctor);
        return "admin/doctorForm";
    }

    /**
     * 医生编辑
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String doctorEditForm(@PathVariable Integer id,Model model) {
        model.addAttribute("doctor",lbDoctorService.findOne(id));
        return "admin/doctorForm";
    }

    /**
     * 异步插入记录
     */
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseResult insert(@RequestBody LbDoctor lbDoctor) {
        return lbDoctorService.insertDoctor(lbDoctor);
    }

    /**
     * 异步更新记录
     * @param lbDoctor
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseResult update(@RequestBody LbDoctor lbDoctor) {
        return lbDoctorService.updateDoctor(lbDoctor);
    }

    /**
     * 异步删除
     */
    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseResult delete(@PathVariable Integer id){
        return lbDoctorService.deleteDoctor(id);
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
