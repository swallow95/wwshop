package com.wqy.wwshop.dao;

import com.wqy.wwshop.common.dto.Result;
import com.wqy.wwshop.pojo.vo.TbItemParamCustom;

import java.util.List;
import java.util.Map;

public interface TbItemParamCustomMapper {
    List<TbItemParamCustom> listItemParamsByPage(Map<String, Object> map);

    int countItemParams();
}
