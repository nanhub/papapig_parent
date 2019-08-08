package com.pppig.user.service;

import com.pppig.user.pojo.UserMain;

import java.util.Map;

public interface UserService {
    /**
     *根据userId查询对象
     * @param userId
     * @return
     */
    UserMain findOne(Integer userId);

    /**
     * 开户封装参数
     * @param map
     * @return
     */
    Map<String ,String > accountOpen(Map<String,String> map);


}
