package com.wqy.wwshop.search.web;

import com.wqy.wwshop.pojo.po.TbUser;
import com.wqy.wwshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserAction {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/useradd")
    public String add(TbUser user){
        int save = userService.save(user);
        System.out.println(save);
        return "user/test.jsp";

    }
}
