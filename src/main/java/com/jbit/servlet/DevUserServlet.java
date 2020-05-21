package com.jbit.servlet;

import com.jbit.entity.DevUser;
import com.jbit.service.DevUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("dev")
public class DevUserServlet {
    @Resource
    private DevUserService devUserService;

    @RequestMapping("/login")
    public String login(HttpSession session, Model model, DevUser devUser){
        DevUser devUser1 = devUserService.queryLogin(devUser);
        if(devUser1!=null){
            session.setAttribute("devuser",devUser1);
            return "jsp/developer/main";
        }else{
            model.addAttribute("error","用户名或密码错误");
        }
        return "jsp/devlogin";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/jsp/devlogin.jsp";
    }
}
