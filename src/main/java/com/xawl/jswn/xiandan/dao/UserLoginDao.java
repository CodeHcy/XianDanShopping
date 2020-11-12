package com.xawl.jswn.xiandan.dao;

import com.xawl.jswn.xiandan.domain.BUser;

public interface UserLoginDao {
    BUser selectUser(String name);
}
