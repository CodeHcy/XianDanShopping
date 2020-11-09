package com.xawl.jswn.xiandan.dao;

import org.apache.ibatis.annotations.Select;

public interface UserDao {
    @Select("select bname from buser where bid = 1 ")
    String getUserName();
}
