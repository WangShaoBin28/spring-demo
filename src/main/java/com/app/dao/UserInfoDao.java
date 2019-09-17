package com.app.dao;

import com.app.entity.UserInfoEntity;

/**
 * @Package com.app.dao
 * @ClassName UserInfoDao
 * @Author shaobin.wang
 * @Date 2019/09/17 15:59
 * @Version 1.0
 * @Description:
 **/
public interface UserInfoDao {

    UserInfoEntity findByUsername(String name);
}
