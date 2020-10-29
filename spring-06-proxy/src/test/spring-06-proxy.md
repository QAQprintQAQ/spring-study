#spring-06-proxy
##demo1
###没有使用代理之前 (normal)
###normal
1.在src->main->java下新建一个com.liwenli.normal包

2.在com.liwenli.normal包下新建Client.java,Host.java,Rent.java(接口)

3.编写代码
* Rent.java
~~~~
package com.liwenli.proxydemo;

//租房的接口
public interface Rent {

    public void rent();

}

~~~~

* Host.java
~~~~
package com.liwenli.normal;

public class Host implements Rent {
    @Override
    public void rent() {
        System.out.println("房东要出租房子");

    }
}

~~~~
* Client.java
~~~~
package com.liwenli.normal;

public class Client {
    public static void main(String[] args) {
        Host host = new Host();
        host.rent();
    }

}

~~~~

##使用了代理之后(proxydemo)
###proxydemo
1.在src->main->java下新建一个com.liwenli.proxydemo包

2.在com.liwenli.proxydemo 下新建Client.java, Host.java,Proxy.java,Rent.java(接口)

3.编写代码
* Rent.java
~~~~
package com.liwenli.proxydemo;

//租房的接口
public interface Rent {

    public void rent();

}

~~~~

* Host.java
~~~~
package com.liwenli.proxydemo;

public class Host implements Rent {
    @Override
    public void rent() {


        System.out.println("房东要出租房子");

    }
}

~~~~

* Proxy.java
~~~~
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

~~~~

* Client.java
~~~~
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

~~~~

##demo2
##没有使用代理(proxy)之前(normal2)
1.在src->main->java下新建一个com.liwneli.normal2包

2.在com.liwenli.normal2下新建Client.java,UserService.java(接口),UserSerciceImpl.java

3.编写代码
* UserService.java
~~~~
package com.liwenli.normal2;

public interface UserService {

   public void add();
   public void delete();
    public void update();
    public void query();
}

~~~~

* UserServiceImpl.java
~~~~
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

~~~~

* Client.java
~~~~
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

~~~~

###proxydemo2
1.在src->main->java下新建一个com.liwenli.proxydemo2包

2.在com.liwenli.proxydemo2下新建Client.java,UserSercice.java(接口),UserServiceImpl.java,UserServiceProxy.java

3.编写代码

* UserService.java
~~~~
package com.liwenli.proxydemo2;

/*
    抽象角色

 */
public interface UserService {

   public void add();
   public void delete();
    public void update();
    public void query();
}

~~~~

* UserServiceImpl.java
~~~~
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

~~~~

* UserServiceProxy.java
~~~~
package com.liwenli.proxydemo2;

/*
代理角色
 */
public class UserServiceProxy implements UserService {
    private UserServiceImpl userServiceImpl;

//  不建议这样写,spring建议用set()方法对userServiceImpl进行初始化
//    public UserServiceProxy(UserServiceImpl userServiceImpl) {
//        this.userServiceImpl = userServiceImpl;
//    }

    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {//建议用这种方法初始化
        this.userServiceImpl = userServiceImpl;
    }


    @Override
    public void add() {
        log("add");
        userServiceImpl.add();

    }

    @Override
    public void delete() {
        log("delete");
        userServiceImpl.delete();
    }

    @Override
    public void update() {
        log("update");
        userServiceImpl.update();
    }

    @Override
    public void query() {
        log("query");
        userServiceImpl.query();
    }

    /*
        如果要增加需求,则建议在代理角色下增加方法,而不改变原来的代码!!!
     */

    public void log(String msg) {

        System.out.println("使用了"+msg+"方法");
    }
}

~~~~

* Client.java
~~~~
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

~~~~

##小结
~~~~

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

~~~~






