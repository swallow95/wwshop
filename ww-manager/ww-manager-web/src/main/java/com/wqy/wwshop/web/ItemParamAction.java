package com.wqy.wwshop.web;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.sun.org.apache.xpath.internal.SourceTree;
import com.wqy.wwshop.common.dto.Page;
import com.wqy.wwshop.common.dto.Result;
import com.wqy.wwshop.pojo.po.TbItemParam;
import com.wqy.wwshop.pojo.vo.TbItemParamCustom;
import com.wqy.wwshop.service.ItemParamService;
import org.apache.activemq.usage.StoreUsage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemParamAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/itemParams")
    @ResponseBody
    public Result<TbItemParamCustom> listItemParamsByPage(Page page) {
        Result<TbItemParamCustom> result = null;
        try {
            result = itemParamService.listItemParamsPage(page);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();

        }
        return result;

    }
    //添加
    @ResponseBody
    @RequestMapping("/paramSave/{cid}")
    public int paramSave(TbItemParam tbItemParam, @PathVariable("cid") Long itemId) {
        System.out.println(tbItemParam.getParamData());
        System.out.println("id" + itemId);

        int i = 0;
        try {
            i = itemParamService.paramSave(tbItemParam, itemId);


        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }

}
