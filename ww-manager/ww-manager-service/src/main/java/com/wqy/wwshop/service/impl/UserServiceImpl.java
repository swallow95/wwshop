package com.wqy.wwshop.service.impl;

import com.wqy.wwshop.common.util.IDUtils;
import com.wqy.wwshop.dao.TbUserMapper;
import com.wqy.wwshop.pojo.po.TbUser;
import com.wqy.wwshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper userDao;
    @Override
    public int save(TbUser user) {
        user.setId(IDUtils.getItemId());
        user.setCreated(new Date());
        user.setUpdated(new Date());
        int insert = userDao.insert(user);
        return insert;
    }
}
