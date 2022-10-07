package com.lb.controller.admin;

import com.lb.entity.LbUser;
import com.lb.service.LbUserService;
import com.lb.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName AdminController.java
 * @Description 管理员用户控制器
 * @createTime 2020年03月26日 13:49:00
 */
@Controller
@RequestMapping("/admin/user")
public class AdminController {
    @Autowired
    private LbUserService lbUserService;

    @RequestMapping("/manage")
    public String manage(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                         @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                         @RequestParam(required = false) String username,
                         Model model) {
        //分页查询
        PageQuery<LbUser> page = lbUserService.findList(pageNo,pageSize,username);
        model.addAttribute("page",page);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("username",username);
        model.addAttribute("path","/admin/user/manage");
        return "admin/adminManage";
    }

    /**
     * 新增
     */
    @RequestMapping("/")
    public String addForm(LbUser lbUser,Model model) {
        model.addAttribute("admin",lbUser);
        return "admin/adminForm";
    }

    /**
     * 编辑
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("admin",lbUserService.findOne(id));
        return "admin/adminForm";
    }

    /**
     * 异步插入记录
     */
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseResult insert(@RequestBody LbUser lbUser) {
        return lbUserService.insertUser(lbUser);
    }

    /**
     * 异步更新记录
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseResult update(@RequestBody LbUser lbUser) {
        return lbUserService.updateUser(lbUser);
    }

    /**
     * 异步删除
     */
    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseResult delete(@PathVariable Integer id){
        return lbUserService.deleteUser(id);
    }

}
