package com.liwenli.proxydemo;

public class Proxy implements Rent{

    private Host host;//组合

//    public Proxy() {
//
//    }

//    public Proxy(Host host) {//不推荐这种方式
//        this.host = host;
//    }


    public void setHost(Host host) {//推荐这种初始化Host host的方式
        this.host = host;
    }

    @Override
    public void rent() {
        host.rent();//组合
    }

    public void assocation() {
        System.out.println("中介和你签合同");
    }

    public void seeHost() {
        System.out.println("中介带你看房");
    }

    public void fare() {
        System.out.println("中介收取中介费");
    }

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


}
