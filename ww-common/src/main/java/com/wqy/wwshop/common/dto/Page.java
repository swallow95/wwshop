package com.wqy.wwshop.common.dto;
/***
 * 封装分页请求参数类
 * */
public class Page {
    //当前页的页码
    private int page;
    //每页显示的条数
    private int rows;
    //偏移量（当前页的第一条记录的索引号）
   // private int offset;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
    //获取偏移量，不需要手工设置
    public int getOffset() {
        return (page-1)*rows;
    }


}
