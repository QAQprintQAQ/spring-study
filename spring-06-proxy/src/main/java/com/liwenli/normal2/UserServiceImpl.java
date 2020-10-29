package com.liwenli.normal2;

public class UserServiceImpl implements UserService {

    @Override
    public void add() {
        System.out.println("add方法");//新增的代码,违反了规则,修改了源码,正确的方式应该增加一个代理角色帮忙增加这些代码
        System.out.println("增加了一个用户");
    }

    @Override
    public void delete() {
        System.out.println("delete方法");//新增的代码,违反了规则,修改了源码,正确的方式应该增加一个代理角色帮忙增加这些代码
        System.out.println("删除了一个用户");

    }

    @Override
    public void update() {
        System.out.println("update方法");//新增的代码,违反了规则,修改了源码,正确的方式应该增加一个代理角色帮忙增加这些代码
        System.out.println("修改了一个用户");
    }

    @Override
    public void query() {
        System.out.println("query方法");//新增的代码,违反了规则,修改了源码,正确的方式应该增加一个代理角色帮忙增加这些代码
        System.out.println("查询了一个用户");

    }
}
