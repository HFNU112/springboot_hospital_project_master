package com.lb.controller.home;

import com.lb.entity.LbUser;
import com.lb.service.LbUserService;
import com.lb.vo.ActiveUser;
import com.lb.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 *
 */
@Controller
@RequestMapping("/home/user")
public class LoginController {
    @Autowired
    private LbUserService lbUserService;

    /**
     * 登录页面
     */
    @RequestMapping("/login")
    public String loginRegisterPage() {
        return "home/login&register";
    }

    /**
     * 请求登录
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult login(@RequestBody LbUser user, HttpSession session) {
        return lbUserService.checkUser(user,session);
    }

    /**
     * 注册用户
     */
    @ResponseBody
    @RequestMapping("/register")
    public ResponseResult register(@RequestBody ActiveUser activeUser){
        return lbUserService.registUser(activeUser);
    }

    /**
     * 安全退出
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // 清除sessin
        session.invalidate();
        return "redirect:/home/user/login";
    }
}
