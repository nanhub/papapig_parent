package com.pppig.user.service.impl;

import com.pppig.user.dao.UserDao;
import com.pppig.user.pojo.UserMain;
import com.pppig.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public UserMain findOne(Integer userId) {
        return userDao.findOne(userId);
    }
}
