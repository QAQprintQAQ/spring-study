package com.liwenli.proxydemo2;

/*
客户
 */
public class Client {
    public static void main(String[] args) {


        UserServiceProxy userServiceProxy = new UserServiceProxy();
        userServiceProxy.setUserServiceImpl(new UserServiceImpl());
        userServiceProxy.add();

    }

}
