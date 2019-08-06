package com.pppig.user.dao;

import com.pppig.user.pojo.UserMain;
import org.apache.ibatis.annotations.Insert;

public interface LoginDao {

    //注册
    @Insert("insert into user_main values (#{userId},#{email},#{nickName},#{realName},#{idCardNo},#{passwd},#{payPassword},#{isPwdSet},#{mobile},#{jxmoblie},#{city},#{registerTime},#{roles},#{cash},#{currBal},#{frozenWithDrawCash},#{frozenBiddingCash},#{idVerifyLimit},#{portrait},#{status},#{forbidStatus},#{albumCapacity},#{securityLevel},#{weiboUId},#{weiboAccessToken},#{qqUId},#{qqAccessToken},#{origin},#{staffId},#{userCode},#{referee},#{level_update_time},#{level},#{bindtime},#{loginkey},#{userkey},#{cashChl})")
    void InsertUserMain(UserMain userMain);
}
