package com.wqy.wwshop.dao;

import com.wqy.wwshop.common.dto.Page;
import com.wqy.wwshop.pojo.po.TbItem;

import java.util.List;

//自定义的商品实体数据访问层接口
public interface TbItemCustomMapper {
    //查询商品表中所有记录的数量
    int countItems();
    //查询指定页码显示记录的集合
    List<TbItem> listItemByPage(Page page);

}
