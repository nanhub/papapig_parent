package com.pppig.user.service;

import com.pppig.user.pojo.UserMain;

public interface UserService {
    /**
     *根据userId查询对象
     * @param userId
     * @return
     */
    UserMain findOne(Integer userId);
}
