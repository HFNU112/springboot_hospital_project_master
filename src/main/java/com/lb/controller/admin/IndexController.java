package com.lb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 蓝莲花
 * @version 1.0.0
 * @ClassName AdminController.java
 * @Description 后台主界面控制器
 * @createTime 2020年03月26日 13:49:00
 */
@Controller
@RequestMapping("/admin")
public class IndexController {
    //主界面
    @RequestMapping("/index")
    public String index() {
        return "admin/index";
    }
}
