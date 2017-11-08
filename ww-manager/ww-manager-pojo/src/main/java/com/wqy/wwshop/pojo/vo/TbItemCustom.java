package com.wqy.wwshop.pojo.vo;

import com.wqy.wwshop.pojo.po.TbItem;
//自定义的商品显示类
public class TbItemCustom extends TbItem{
    private String catName;
    private String priceView;

    public String getPriceView() {
        return priceView;
    }

    public void setPriceView(String priceView) {
        this.priceView = priceView;
    }
    /* private String statusName;


    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }*/

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
