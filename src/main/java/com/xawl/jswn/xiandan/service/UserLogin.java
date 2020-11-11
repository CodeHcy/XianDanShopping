package com.xawl.jswn.xiandan.service;

import com.xawl.jswn.xiandan.dao.UserLoginDao;
import com.xawl.jswn.xiandan.domain.loginUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLogin {
    @Autowired
    UserLoginDao userLoginDao;
    public loginUser selectUser(String name){
        return userLoginDao.selectUser(name);
    }
}
