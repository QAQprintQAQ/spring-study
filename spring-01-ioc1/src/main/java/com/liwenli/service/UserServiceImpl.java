package com.liwenli.service;

import com.liwenli.dao.*;

public class UserServiceImpl implements UserService {

/*
     需要service服务端来控制用户的创建,MyTest无法控制!

 */
    private   UserDao userDao = new UserDaoImpl();
    private   UserDao userDao2 = new UserDaoMysqlImpl();
    private   UserDao userDao3 = new UserDaoOracleImpl();
    private   UserDao userDao4 = new UserDaoSqlserverImpl();



    public void getUser() {
        userDao.getUser();
        userDao2.getUser();
        userDao3.getUser();
        userDao4.getUser();

    }
}
