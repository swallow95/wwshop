package com.wqy.wwshop.service.impl;

import com.wqy.wwshop.common.jedis.JedisClient;
import com.wqy.wwshop.common.util.JsonUtils;
import com.wqy.wwshop.dao.TbContentCategoryMapper;
import com.wqy.wwshop.dao.TbContentMapper;
import com.wqy.wwshop.pojo.po.TbContent;
import com.wqy.wwshop.pojo.po.TbContentCategory;
import com.wqy.wwshop.pojo.po.TbContentExample;
import com.wqy.wwshop.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ContentServiceImpl implements ContentService {

     private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbContentMapper contentDao;
    @Autowired
    private JedisClient jedisClient;

    @Override
    public List<TbContent> listContentsByCid(Long id) {
        List<TbContent> list=null;
        //查询缓存 如果存在直接加载
        try {
            String json = jedisClient.hget("CONTENT_LIST", id + "");
            if (StringUtils.isNoneBlank(json)){//不为空 或者‘’
                return JsonUtils.jsonToList(json,TbContent.class);

            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        //创建查询模版//如果缓存中没有
        TbContentExample example=new TbContentExample();
        TbContentExample.Criteria criteria=example.createCriteria();
        criteria.andCategoryIdEqualTo(id);
        //执行查询
        list = contentDao.selectByExample(example);

        //存入缓存
        try {
            jedisClient.hset("CONTENT_LIST",id+"", JsonUtils.objectToJson(list));


        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return list;
    }
}
