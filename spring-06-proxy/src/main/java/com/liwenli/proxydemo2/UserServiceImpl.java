package com.liwenli.proxydemo2;

/*
    静态代理
静态代理角色分析

抽象角色 : 一般使用接口或者抽象类来实现

真实角色 : 被代理的角色

代理角色 : 代理真实角色 ; 代理真实角色后 , 一般会做一些附属的操作 .

客户  :  使用代理角色来进行一些操作 .

 */


//真实角色
public class UserServiceImpl implements UserService {

    @Override
    public void add() {
        System.out.println("增加了一个用户");
    }

    @Override
    public void delete() {
        System.out.println("删除了一个用户");

    }

    @Override
    public void update() {
        System.out.println("修改了一个用户");
    }

    @Override
    public void query() {
        System.out.println("查询了一个用户");

    }
}
