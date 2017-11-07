package com.wqy.wwshop.service.impl;

import com.wqy.wwshop.common.dto.Page;
import com.wqy.wwshop.common.dto.Result;
import com.wqy.wwshop.dao.TbItemCustomMapper;
import com.wqy.wwshop.dao.TbItemMapper;
import com.wqy.wwshop.pojo.po.TbItem;
import com.wqy.wwshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemServiceImpl implements ItemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemMapper itemDao;
    @Autowired
    private TbItemCustomMapper itemCustomDao;


    @Override
    public TbItem getById(long itemId) {
        TbItem tbItem = itemDao.selectByPrimaryKey(itemId);
        return tbItem;
    }

    @Override
    public Result<TbItem> listItemsByPage(Page page) {
        Result<TbItem> result = null;
        try {
            //1 创建一个响应参数实体类
            result=new Result<TbItem>();
             //2 对total设值  总记录数
            int total = itemCustomDao.countItems();
            result.setTotal(total);
            //3  对rows、设值  指定页码
            List<TbItem> list = itemCustomDao.listItemByPage(page);
            result.setRows(list);

        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

   /* @Override
    public List<TbItem> listItem() {
        List<TbItem> list = null;
        try {
            list = itemDao.selectByExample(null);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return list;
    }*/

}
