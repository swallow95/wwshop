package com.wqy.wwshop.service;

import com.wqy.wwshop.pojo.po.TbContent;

import java.util.List;

public interface ContentService {


    List<TbContent> listContentsByCid(Long id);
}
