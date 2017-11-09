package com.wqy.wwshop.dao;

import com.wqy.wwshop.common.dto.Order;
import com.wqy.wwshop.common.dto.Page;
import com.wqy.wwshop.pojo.po.TbItem;
import com.wqy.wwshop.pojo.vo.TbItemCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

//自定义的商品实体数据访问层接口
public interface TbItemCustomMapper {
    //查询商品表中所有记录的数量
    int countItems(Map<String,Object> map);
    //查询指定页码显示记录的集合
   // List<TbItemCustom> listItemByPage(@Param("page") Page page, @Param("order") Order order);
    List<TbItemCustom> listItemByPage(Map<String,Object> map);

}
