package com.wqy.wwshop.common.dto;

import java.util.List;

//封装分页请求响应参数类
public class Result<T> {
    //符合条件的总记录数
    private int total;
    /*指定页码显示记录的集合*/
    private List<T> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
