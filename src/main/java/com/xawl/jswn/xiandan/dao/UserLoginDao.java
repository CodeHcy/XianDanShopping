package com.xawl.jswn.xiandan.dao;

import com.xawl.jswn.xiandan.domain.loginUser;
import org.apache.ibatis.annotations.Select;

public interface UserLoginDao {
    @Select("select * from buser where bname = #{name}")
    loginUser selectUser(String name);
}
