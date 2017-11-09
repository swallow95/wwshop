package com.wqy.wwshop.service.impl;

import com.wqy.wwshop.common.dto.Order;
import com.wqy.wwshop.common.dto.Page;
import com.wqy.wwshop.common.dto.Result;
import com.wqy.wwshop.dao.TbItemCustomMapper;
import com.wqy.wwshop.dao.TbItemMapper;
import com.wqy.wwshop.pojo.po.TbItem;
import com.wqy.wwshop.pojo.po.TbItemExample;
import com.wqy.wwshop.pojo.vo.TbItemCustom;
import com.wqy.wwshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
   public Result<TbItemCustom> listItemsByPage(Page page, Order order) {
       Result<TbItemCustom> result = null;
       try {
           Map<String,Object> map=new HashMap<>();
           map.put("page",page);
           map.put("order",order);
           System.out.println("===="+order.getSort());
           System.out.println(order);
           //1 创建一个响应参数实体类
           result = new Result<TbItemCustom>();
           //2 对total进行设值(符合条件的总记录数)
           int total = itemCustomDao.countItems();
           result.setTotal(total);
           //3 对rows进行设值(指定页码显示记录集合)
           List<TbItemCustom> list = itemCustomDao.listItemByPage(map);
           //System.out.println(list.get(0).getCid());

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


}
