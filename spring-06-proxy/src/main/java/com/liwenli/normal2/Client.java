package com.liwenli.normal2;

public class Client {
    public static void main(String[] args) {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        System.out.println("测试普通的修改源码的不规范的增加代码方式");
        userServiceImpl.add();
        userServiceImpl.delete();
        userServiceImpl.query();
        userServiceImpl.update();
    }

}
