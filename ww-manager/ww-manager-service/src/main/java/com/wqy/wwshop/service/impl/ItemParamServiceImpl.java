package com.wqy.wwshop.service.impl;


import com.wqy.wwshop.common.dto.Page;
import com.wqy.wwshop.common.dto.Result;
import com.wqy.wwshop.dao.TbItemParamCustomMapper;
import com.wqy.wwshop.dao.TbItemParamMapper;
import com.wqy.wwshop.pojo.po.TbItemParam;
import com.wqy.wwshop.pojo.vo.TbItemParamCustom;
import com.wqy.wwshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ItemParamServiceImpl implements ItemParamService{
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemParamCustomMapper itemParamCustomDao;
    @Autowired
    private TbItemParamMapper tbItemParamDao;
    @Override
    public Result<TbItemParamCustom> listItemParamsPage(Page page) {
        Result<TbItemParamCustom> result=null;
        try {
            //DAO层接口多多个参数解决方案之一
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("page", page);
            //取出tb_item_param这张表记录条数
            int count = itemParamCustomDao.countItemParams();
            //取出对应页码的记录集合
            List<TbItemParamCustom> list = itemParamCustomDao.listItemParamsByPage(map);
            result = new Result<>();
            result.setTotal(count);
            result.setRows(list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();

        }

        return result;
    }

    @Override
    public int paramSave(TbItemParam tbItemParam, Long itemId) {
        int i=0;
        try {

            tbItemParam.setItemCatId(itemId);
            tbItemParam.setCreated(new Date());
            tbItemParam.setUpdated(new Date());
            i=tbItemParamDao.insert(tbItemParam);
            System.out.println("===="+i);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }
}
