package com.wqy.wwshop.search.web;

import com.wqy.wwshop.common.dto.Order;
import com.wqy.wwshop.common.dto.Page;
import com.wqy.wwshop.common.dto.Result;
import com.wqy.wwshop.pojo.po.TbItem;
import com.wqy.wwshop.pojo.vo.TbItemCustom;
import com.wqy.wwshop.pojo.vo.TbItemQuery;
import com.wqy.wwshop.service.ItemService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
   /* @ResponseBody
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
    }*/
   @ResponseBody
   @RequestMapping("/items")
   public Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery tbItemQuery) {
       Result<TbItemCustom> list = null;
       try {
           list = itemService.listItemsByPage(page,order,tbItemQuery);
       } catch (Exception e) {
           logger.error(e.getMessage(), e);
           e.printStackTrace();
       }
       return list;
   }
   //删除
   @ResponseBody
   @RequestMapping("/items/batch")
   public int updateBatch(@RequestParam("ids[]") List<Long> ids){
       int i = 0;
       try {
           i = itemService.updateBatch(ids);
       }catch (Exception e){
           logger.error(e.getMessage(), e);
           e.printStackTrace();
       }
       return i;
   }
   //上架
   @RequestMapping("/items/upItems")
    @ResponseBody
    public int updateUpItems(@RequestParam("ids[]") List<Long> ids){
       int i=0;
       try {
           i=itemService.updateBatchUp(ids);
       }catch (Exception e){
           logger.error(e.getMessage(),e);
           e.printStackTrace();
       }
       return i;
   }
   //下架
    @RequestMapping("/Items/ItemDown")
    @ResponseBody
    public int updateItemDown(@RequestParam("ids[]") List<Long> ids){
        int i=0;
        try {
           i= itemService.updateBatchDown(ids);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();

        }
        return i;
    }
    //添加
    @ResponseBody
    @RequestMapping("/item")
    public int saveItem(TbItem tbItem,String content,String paramData){
        System.out.println("商品"+tbItem);
        System.out.println("描述"+content);
        System.out.println(paramData);
        int i=0;
        try {
            i=itemService.saveItem(tbItem,content,paramData);
            System.out.println(i);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }


}
