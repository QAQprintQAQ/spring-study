package com.liwenli.proxydemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/*
    等下我们会用在这个类自动生成代理类

 */
public class ProxyInvocationHandler implements InvocationHandler {

    /*
        被代理的接口
        Rent接口将会被代理
     */
    private Rent rent;

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    /*
        生成得到代理类
     */
    public Object getProxy() {
        //这个代码是固定的,到时候更换代理接口的时候只需要更改rent.getClass这个参数就行了
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), rent.getClass().getInterfaces(), this);

    }


    /*
        处理代理实例,并返回结果
     */
    @Override
    //动态代理的本质就是使用反射机制实现!
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(rent, args);
        seeHose();//代理自己添加的行为
        fare();//只是改变了ProxyInvocationHandler.java的代码,并没有改变Host.java真实角色的代码
        assocation();//只是改变了ProxyInvocationHandler.java的代码,并没有改变Host.java真实角色的代码

        return result;
    }

    public void seeHose() {//ProxyInvocationHandler.java中介代理增加的方法
        System.out.println("中介带你看房");

    }
    public void fare() {//ProxyInvocationHandler.java中介代理增加的方法
        System.out.println("中介收费用");

    }
    public void assocation() {//ProxyInvocationHandler.java中介代理增加的方法
        System.out.println("中介和你协商");

    }

}
