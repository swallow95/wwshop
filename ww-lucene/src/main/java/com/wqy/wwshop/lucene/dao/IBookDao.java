package com.wqy.wwshop.lucene.dao;


import com.wqy.wwshop.lucene.po.Book;

import java.util.List;


public interface IBookDao {
    /**
     * 采集数据：查询所有图书
     * @return
     */
    List<Book> listBooks();
}
