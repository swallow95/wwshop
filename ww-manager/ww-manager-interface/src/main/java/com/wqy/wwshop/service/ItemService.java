package com.wqy.wwshop.service;

import com.wqy.wwshop.common.dto.Order;
import com.wqy.wwshop.common.dto.Page;
import com.wqy.wwshop.common.dto.Result;
import com.wqy.wwshop.pojo.po.TbItem;
import com.wqy.wwshop.pojo.vo.TbItemCustom;
import com.wqy.wwshop.pojo.vo.TbItemQuery;

import java.util.List;

public interface ItemService {

    TbItem getById(long itemId);

   // List<TbItem> listItem();
    //分页
   Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery tbItemQuery);


    //删除
    int updateBatch(List<Long> ids);
    //上架
    int updateBatchUp(List<Long> ids);
    //下架
    int updateBatchDown(List<Long> ids);
    //添加
    int saveItem(TbItem tbItem, String content,String paramData);
}
