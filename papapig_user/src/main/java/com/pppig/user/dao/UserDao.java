package com.pppig.user.dao;

import com.pppig.user.pojo.UserMain;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    //根据userId查询对象
    @Select("SELECT * FROM USER_MAIN WHERE userId = #{userId}")
    UserMain findOne(Integer userId);
}
