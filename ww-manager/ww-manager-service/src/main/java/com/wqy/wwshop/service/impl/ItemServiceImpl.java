package com.wqy.wwshop.service.impl;

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


    @Override
    public TbItem getById(long itemId) {
        TbItem tbItem = itemDao.selectByPrimaryKey(itemId);
        return tbItem;
    }

    @Override
    public List<TbItem> listItem() {
        List<TbItem> list = null;
        try {
            list = itemDao.selectByExample(null);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return list;
    }

}
