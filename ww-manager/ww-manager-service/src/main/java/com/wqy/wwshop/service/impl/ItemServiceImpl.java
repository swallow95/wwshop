package com.wqy.wwshop.service.impl;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.wqy.wwshop.common.dto.Order;
import com.wqy.wwshop.common.dto.Page;
import com.wqy.wwshop.common.dto.Result;
import com.wqy.wwshop.common.util.IDUtils;
import com.wqy.wwshop.dao.*;
import com.wqy.wwshop.pojo.po.*;
import com.wqy.wwshop.pojo.vo.TbItemCustom;
import com.wqy.wwshop.pojo.vo.TbItemQuery;
import com.wqy.wwshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemMapper itemDao;
    @Autowired
    private TbItemCustomMapper itemCustomDao;
    //引入tb_item_desc
    @Autowired
    private TbItemDescMapper tbItemDescDao;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemDao;


    @Override
    public TbItem getById(long itemId) {
        TbItem tbItem = itemDao.selectByPrimaryKey(itemId);
        return tbItem;
    }

   /* @Override
    public Result<TbItemCustom> listItemsByPage(Page page) {
        Result<TbItemCustom> result = null;
        try {
            //1 创建一个响应参数实体类
            result=new Result<TbItemCustom>();
             //2 对total设值  总记录数
            int total = itemCustomDao.countItems();
            result.setTotal(total);
            //3  对rows、设值  指定页码
            List<TbItemCustom> list = itemCustomDao.listItemByPage(page);
            result.setRows(list);

        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }*/
   //分页
   @Override
   public Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery tbItemQuery) {
       Result<TbItemCustom> result = null;
       try {
           Map<String,Object> map=new HashMap<>();
           map.put("page",page);
           map.put("order",order);
           map.put("tbItemQuery",tbItemQuery);
          // System.out.println("===="+order.getSort());
          // System.out.println("模糊标题="+tbItemQuery.getTitle());
         //  System.out.println("模糊状态="+tbItemQuery.getStatus());

           //1 创建一个响应参数实体类
           result = new Result<TbItemCustom>();
           //2 对total进行设值(符合条件的总记录数)
           int total = itemCustomDao.countItems(map);
           result.setTotal(total);
          // System.out.println("总数"+total);
           //3 对rows进行设值(指定页码显示记录集合)
           List<TbItemCustom> list = itemCustomDao.listItemByPage(map);
           System.out.println(list.get(0));
          // System.out.println("每页的大小"+page.getRows());
          // System.out.println("每页的位偏移"+page.getOffset());
           result.setRows(list);
       }catch (Exception e) {
           logger.error(e.getMessage(), e);
           e.printStackTrace();
       }
       return result;
   }
   //删除
    @Override
    public int updateBatch(List<Long> ids) {
        int i = 0;
        try {
            //准备商品对象，这个对象包含了状态为3的字段
            TbItem record = new TbItem();
            record.setStatus((byte) 3);
            //创建更新模板 update tb_item set status=? where id in (?,?,?)
            TbItemExample example = new TbItemExample();
            TbItemExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);
            //执行更新
            i = itemDao.updateByExampleSelective(record, example);
            //System.out.println("======"+i);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }
    //上架
    @Override
    public int updateBatchUp(List<Long> ids) {
       int i=0;
       try {
           TbItem record=new TbItem();
           record.setStatus((byte)1);
           TbItemExample example=new TbItemExample();
           TbItemExample.Criteria criteria=example.createCriteria();
           criteria.andIdIn(ids);
           i=itemDao.updateByExampleSelective(record,example);
           System.out.println("上架"+i);
       }catch (Exception e){
           logger.error(e.getMessage(),e);
           e.printStackTrace();
       }
        return i;
    }
    //下架
    @Override
    public int updateBatchDown(List<Long> ids) {
       int i=0;
       try {
           TbItem record=new TbItem();
           record.setStatus((byte)2);
           TbItemExample example=new TbItemExample();
           TbItemExample.Criteria criteria=example.createCriteria();
           criteria.andIdIn(ids);
           i=itemDao.updateByExampleSelective(record,example);
           System.out.println("下架"+i);
       }catch (Exception e){
           logger.error(e.getMessage(),e);
           e.printStackTrace();
       }
        return i;
    }

    //加上注解@Transactional之后，这个方法就变成了事务方法
    //并不是事务方法越多越好，查询方法不需要添加为事务方法
    @Transactional
    @Override
    public int saveItem(TbItem tbItem, String content,String paramData) {
       int i=0;
       try{
           //这个方法中需要处理两张表格tb_item tb_item_desc tb_item_param_item
           //调用工具类生成商品的ID
           //处理tb_item
           Long itemId= IDUtils.getItemId();
           System.out.println("id===="+itemId);
           tbItem.setId(itemId);
           tbItem.setStatus((byte)2);
           tbItem.setCreated(new Date());
           tbItem.setUpdated(new Date());
           //保存商品
           i = itemDao.insert(tbItem);
           System.out.println("===="+i);
           //处理tb_item_desc
           TbItemDesc tbItemDesc=new TbItemDesc();
           tbItemDesc.setItemId(itemId);
           tbItemDesc.setCreated(new Date());
           tbItemDesc.setUpdated(new Date());
          // System.out.println(1/0);  这里不能写try catch  出了异常还自行处理   不符合
           //保存描述  i+是为了到页面判断是否添加成功
           i+= tbItemDescDao.insert(tbItemDesc);
           //System.out.println("iiiii===="+i);
           TbItemParamItem tbItemParam=new TbItemParamItem();
        tbItemParam.setItemId(itemId);
          tbItemParam.setCreated(new Date());
          tbItemParam.setUpdated(new Date());
          tbItemParam.setParamData(paramData);
         i+= tbItemParamItemDao.insert(tbItemParam);

       }catch (Exception e){
           logger.error(e.getMessage(),e);
           e.printStackTrace();
       }
        return i;
    }


}
