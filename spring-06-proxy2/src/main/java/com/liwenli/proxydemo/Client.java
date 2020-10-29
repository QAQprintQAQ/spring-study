package com.liwenli.proxydemo;

public class Client {
    public static void main(String[] args) {


         /*
            抽象角色 : 一般使用接口或者抽象类来实现

            真实角色 : 被代理的角色

            代理角色 : 代理真实角色 ; 代理真实角色后 , 一般会做一些附属的操作 .

            客户  :  使用代理角色来进行一些操作 .
        */

        /*

             抽象角色(Rent.java),代理角色(Proxy.java)和真实角色(Host.java)的共同任务,都需要实现这个接口
             真实角色(Host.java)要出租房子
             代理角色(Proxy.java),相当于中介帮房东(真实角色)租房子,但是呢,代理角色一般会有一些附属操作!
             客户角色(Client.java)你不用面对真实角色(Host.java),直接找代理角色(Proxy.java)就行

         */

        //真实角色
        Host host = new Host();
        //代理角色:现在没有
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();
        //通过调用程序处理角色来处理我们要调用的接口对象!
        proxyInvocationHandler.setRent(host);

//        Object proxy = proxyInvocationHandler.getProxy();
        //强转为自己需要的抽象角色接口,这里是Rent.java接口
        Rent proxy = (Rent) proxyInvocationHandler.getProxy();
        proxy.rent();

    }

}
