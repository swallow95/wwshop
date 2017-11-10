package com.wqy.wwshop.web;

import com.wqy.wwshop.common.dto.TreeNode;
import com.wqy.wwshop.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class ItemCatAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemCatService itemCatService;
    @RequestMapping("/itemCats")
    @ResponseBody
    public List<TreeNode> listItemCatsByPid(@RequestParam("parentId") Long parentId) {
        List<TreeNode> list=null;
        try {
           list= itemCatService.listItemCatsByPid(parentId);
           System.out.print(list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
              return  list;
    }
}

