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



        /*
             静态代理的好处:

            可以使得我们的真实角色更加纯粹 . 不再去关注一些公共的事情 .

            公共的业务由代理来完成 . 实现了业务的分工 ,

            公共业务发生扩展时变得更加集中和方便 .

            缺点 :

            类多了 , 多了代理类 , 工作量变大了 . 开发效率降低 .


         */



         /*

            Host host = new Host();
            Proxy proxy = new Proxy(host);可以用下面的替换 Proxy proxy = new Proxy(new Host());

         */

//        Proxy proxy = new Proxy(new Host());//不推荐这种方式
//                 proxy.rent();

        Proxy proxy = new Proxy();
        proxy.setHost(new Host());
        proxy.rent();
    }

}
