#spring-06-proxy2
* 动态代理

##demo
##proxydemo
1.在src->main->java下新建一个com.liwenli.proxydemo

2.在com.liwenli.proxydemo下新建Rent.java(接口),Host.java,ProxyInvocationHandler.java,Client.java

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

* ProxyInvocationHandler.java
~~~~
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

~~~~

##proxydemo2
1.在src->main->java下新建一个com.liwenli.proxydemo2

2.在com.liwenli.proxydemo2下新建UserService.java(接口),UserServiceImpl.java,ProxyInvocationHandler.java,Client.java

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

* ProxyInvocationHandler.java
~~~~
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

~~~~

* Client.java
~~~~
package com.liwenli.proxydemo2;

/*
客户
 */
public class Client {
    public static void main(String[] args) {

        UserServiceImpl userServiceImpl = new UserServiceImpl();//真实对象

//        代理角色:还不存在
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();
        proxyInvocationHandler.setTarget(userServiceImpl);//设置要代理的对象为userServiceImpl
//        Object proxy = proxyInvocationHandler.getProxy();//默认返回的是Objecct对象,这里需要我们自己将Object强转为自己需要的类型接口这里是UserService
        //下面一定要强转为自己需要的抽象接口,这里是UserService.java接口
        UserService proxy =(UserService) proxyInvocationHandler.getProxy();//动态地生成一个proxy代理角色
        proxy.add();
        proxy.query();
        proxy.update();
        proxy.delete();

    }

}

~~~~

##小结
