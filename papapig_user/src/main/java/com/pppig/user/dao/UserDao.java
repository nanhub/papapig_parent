package com.pppig.user.dao;

import com.pppig.user.pojo.UserMain;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {

    //根据userId查询对象
    @Select("SELECT * FROM USER_MAIN WHERE userId = #{userId}")
    UserMain findOne(Integer userId);

    //根据手机号查询
    @Select("select * from user_main where jxmobile = #{jxmobile}")
    List<UserMain> selectUserMainJxobile(UserMain userMain);

    //根据身份证查询
    @Select("select * from user_main where idCardNo = #{idCardNo}")
    List<UserMain> selectUserMainIdCardNo(UserMain userMain);
}
