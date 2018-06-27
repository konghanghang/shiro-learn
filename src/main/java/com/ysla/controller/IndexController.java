package com.ysla.controller;

import com.ysla.vo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "welcome to shiro";
    }

    @RequestMapping(value = "/login1", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(),
                user.getPassword());
        try {
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }
        if (subject.hasRole("admin")){
            return "welcome admin";
        }
        return "login success,welcome to shiro!";
    }

}
