package com.liwenli.service;

import com.liwenli.dao.UserDao;

public class UserServiceImpl implements UserService {


    private  UserDao userDao;
    /*
    我们并没有像spring-01-ioc1那样,在这里就创建了一个UserDao.java的实现类
    我们这里向外部提供了一个set方法,这样只要MyTest.java中需要新建UserDao实现类就可以调用这个方法从而创建一个UserDao实现类
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUser() {
        userDao.getUser();

    }


}
