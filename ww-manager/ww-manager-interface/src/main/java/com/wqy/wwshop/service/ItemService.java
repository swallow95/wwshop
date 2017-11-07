package com.wqy.wwshop.service;

import com.wqy.wwshop.pojo.po.TbItem;

import java.util.List;

public interface ItemService {

    TbItem getById(long itemId);

    List<TbItem> listItem();
}
