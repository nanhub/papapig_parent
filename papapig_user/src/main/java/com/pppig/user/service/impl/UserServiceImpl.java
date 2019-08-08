package com.pppig.user.service.impl;

import com.pppig.user.dao.UserDao;
import com.pppig.user.pojo.UserMain;
import com.pppig.user.service.LoginService;
import com.pppig.user.service.UserService;
import common.Common;
import common.Constant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;



    @Override
    public UserMain findOne(Integer userId) {
        return userDao.findOne(userId);
    }

    @Override
    public Map<String, String> accountOpen(Map<String, String> OPMap) {
        Map<String, String> map =  new HashMap<>();

        String userId = OPMap.get("userId");
        Integer id = Integer.valueOf(userId);
        UserMain userMain = new UserMain();

        //校验是否注册
        UserMain usermain = userDao.findOne(id);
        if(usermain==null){
            map.put("no","未注册");
            return map;
        }
        //验证是否开过户
        if(!StringUtils.isBlank(usermain.getUserCode())){
            map.put("no","已开户");
            return map;
        }
        //手机号是否开通过电子账户
        String mobile = OPMap.get("mobile");
        usermain.setJxmobile(mobile);
        List<UserMain> selectUserMainMobile = userDao.selectUserMainJxobile(userMain);
        if(!selectUserMainMobile.isEmpty()){
            map.put("no","手机号已开通过电子账户");
            return map;
        }

        //验证身份证是否申请
        String idCardNo = OPMap.get("idCardNo");
        usermain.setIdCardNo(idCardNo);
        List<UserMain> selectUserMainIdCardNo = userDao.selectUserMainIdCardNo(usermain);
        if(!selectUserMainIdCardNo.isEmpty()){
            map.put("no","身份证已经申请");
            return map;
        }

        //封装参数
        OPMap.put("version", Constant.version);
        OPMap.put("txCode", Constant.txCode);
        OPMap.put("instCode", Constant.instCode);
        OPMap.put("bankCode", Constant.bankCode);
        OPMap.put("txDate", new Common().todate());
        OPMap.put("txTime", new Common().totime());
        OPMap.put("seqNo", new Common().Random());
        OPMap.put("channel", Constant.channel);
        OPMap.put("acctUse", Constant.acctUse);
        OPMap.put("idType", Constant.idType);
        OPMap.put("email", "");
        OPMap.put("retUrl", "index");
        OPMap.put("notifyUrl", "10.1.67.28:9008/ElectronicAccount");
        OPMap.put("userIP", "");
        OPMap.put("acqRes", "");

        //三码校验
        //PhonecodeUtil.gethone(idCardNo, name, mobile);
        return OPMap;
    }


}
