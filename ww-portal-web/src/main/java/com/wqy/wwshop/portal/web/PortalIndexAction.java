package com.wqy.wwshop.portal.web;

import com.wqy.wwshop.common.util.PropKit;
import com.wqy.wwshop.pojo.po.TbContent;

import com.wqy.wwshop.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
public class PortalIndexAction {
    @Autowired
    private ContentService contentService;
    @RequestMapping("/")
    public String portalIndex(Model model){
        //第一步：使用service去查  根据tb_content_category的id去查
        Long id=PropKit.use("ftp.properties").getLong("ftp.gallery");
        List<TbContent> list=contentService.listContentsByCid(id);
        model.addAttribute("ad1List",list);
        return "index";


    }
}
