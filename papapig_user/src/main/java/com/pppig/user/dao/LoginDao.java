package com.pppig.user.dao;

import com.pppig.user.pojo.UserMain;
import org.apache.ibatis.annotations.Insert;

public interface LoginDao {

    //注册
    @Insert("insert into user_main (mobile,passwd,registerTime,roles,referee)values (#{mobile},#{passwd},#{registerTime},#{roles},#{referee})")
    void InsertUserMain(UserMain userMain);
}
