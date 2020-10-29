package com.liwenli.proxydemo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
动态代理的好处
静态代理有的它都有，静态代理没有的，它也有！

可以使得我们的真实角色更加纯粹 . 不再去关注一些公共的事情 .

公共的业务由代理来完成 . 实现了业务的分工 ,

公共业务发生扩展时变得更加集中和方便 .

一个动态代理 , 一般代理某一类业务

一个动态代理可以代理多个类，代理的是接口！

 */

/*
    等下我们会用在这个类自动生成代理类

 */
public class ProxyInvocationHandler implements InvocationHandler {

    /*
        被代理的接口
        Rent接口将会被代理
     */
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    /*
        生成得到代理类
     */
    public Object getProxy() {
        //这个代码是固定的,到时候更换代理接口的时候只需要更改rent.getClass这个参数就行了
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);

    }


    /*
        处理代理实例,并返回结果
     */
    @Override
    //动态代理的本质就是使用反射机制实现!
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        method:要执行的目标对象的方法
//        log("add");//这种是不智能的,只能写成固定的形式,如果调用了delete()也会显示是 "调用了add方法" 。
        log(method.getName());//这样就是会调用add()方法就显示"调用了add方法"。。。

        Object result = method.invoke(target, args);

        return result;
    }

    public void log(String msg) {
        System.out.println("调用了" + msg + "方法");
    }

}
