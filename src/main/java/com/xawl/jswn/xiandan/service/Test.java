package com.xawl.jswn.xiandan.service;

import com.xawl.jswn.xiandan.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class Test implements deome{
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;
    @Override
    public String getUserName() {
        return userDao.getUserName();
    }
}
