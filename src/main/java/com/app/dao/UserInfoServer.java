package com.app.dao;

import com.app.entity.UserInfoEntity;
import org.springframework.stereotype.Component;

/**
 * @Package com.app.dao
 * @ClassName UserInfoServer
 * @Author shaobin.wang
 * @Date 2019/09/17 16:01
 * @Version 1.0
 * @Description:
 **/
@Component
public class UserInfoServer implements UserInfoDao {


    @Override
    public UserInfoEntity findByUsername(String name) {
        UserInfoEntity user = new UserInfoEntity();
        user.setUserName("root");
        user.setPassWord("$2a$10$3t.0mqG7K1StPvKAkW1v.uDo361lSs7JIXcdzNj8hjsYBNZCXAegW");

        return user;
    }
}
