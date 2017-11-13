package com.wqy.wwshop.common.dto;

public class TreeNode {
    private Long id;//看easyui的那个demo网页   tree-data  就是上一级目录
    private String text;//内容
    private String state;//与表中is_parent对应  是否是父节点

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
