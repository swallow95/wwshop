package com.wqy.wwshop.service;


import com.wqy.wwshop.common.dto.Page;
import com.wqy.wwshop.common.dto.Result;
import com.wqy.wwshop.pojo.po.TbItemParam;
import com.wqy.wwshop.pojo.vo.TbItemParamCustom;

public interface ItemParamService {

    Result<TbItemParamCustom> listItemParamsPage(Page page);

    int paramSave(TbItemParam tbItemParam, Long itemId);

    TbItemParam getItemParamByCid(Long cid);
}
