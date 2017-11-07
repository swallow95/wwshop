package com.wqy.wwshop.web;

import com.wqy.wwshop.pojo.po.TbItem;
import com.wqy.wwshop.service.ItemService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@Scope("prototype")
public class ItemAction {
   // private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/item/{itemId}",method = RequestMethod.GET)
    @ResponseBody
    public TbItem getById(@PathVariable("itemId") long itemId){
        System.out.println(itemId);
        TbItem tbItem=itemService.getById(itemId);

        System.out.println(tbItem);
        return tbItem;
    }
    @ResponseBody
    @RequestMapping("/items")
    public List<TbItem> listItem(){
        List<TbItem> list=null;
        try {
            list= itemService.listItem();
        }catch (Exception e) {
            logger.error(e.getLocalizedMessage(),e);
            e.printStackTrace();
        }
        return list;
    }


}
