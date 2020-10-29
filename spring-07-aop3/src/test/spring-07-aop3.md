#spring-07-aop3
##service
1.在src->main->java中新建一个com.liwenli.service包

2.在com.liwenli.service中新建UserService.java(接口),UserServiceImpl.java

3.编写代码
* UserService.java
~~~~
package com.liwenli.service;

public interface UserService {
    public void add();
    public void delete();
    public void update();
    public void select();
}

~~~~

* UserServiceImpl.java
~~~~
package com.liwenli.service;

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
    public void select() {
        System.out.println("查询了用户");

    }
}

~~~~

##diy
1.在src->main->java中新建一个com.liwenli.diy包

2.在com.liwenli.diy包下新建一个AnnotationPointCut.java

3.编写AnnotationPointCut.java

~~~~
package com.liwenli.diy;

/*
* 方式三:通过使用注解方式实现aop
* */

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect//标注这个类是一个切面
public class AnnotationPointCut {

    @Before("execution(* com.liwenli.service.UserServiceImpl.*(..))")//切入点的位置就是在UserServiceImpl中
    public void before() {
        System.out.println("==========方法执行前===========");

    }


    @After("execution(* com.liwenli.service.UserServiceImpl.*(..))")
    public void after() {
        System.out.println("=========方法执行后============");

    }

    //    在环绕增强中，我们可以给定一个参数,代表我们要获取处理切入的点
    @Around("execution(* com.liwenli.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("环绕前");

        joinPoint.getSignature();//获得签名
        Object proceed = joinPoint.proceed();//执行方法
        System.out.println("环绕后");

        System.out.println(proceed);

    }

}

~~~~

##ApplicationContext.xml
1.在src->main-resources下新建一个ApplicationContext.xml

2.编写ApplicationContext.xml

~~~~
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">


<!--    注册bean-->
    <bean id="userServiceImpl" class="com.liwenli.service.UserServiceImpl"/>
    <bean id="afterLog" class="com.liwenli.log.AfterLog"/>
    <bean id="beforeLog" class="com.liwenli.log.BeforeLog"/>

    <!--    方式一:使用原生spring API接口-->
    <!--    配置aop:需要导入aop的约束-->
<!--    <aop:config>-->
<!--     切入点:expresesion:表达式,execution(要执行的位置！* * * * *)   -->
<!--        <aop:pointcut id="pointcut" expression="execution(* com.liwenli.service.UserServiceImpl.*(..))"/>-->
<!--        <aop:pointcut id="pointcut2" expression="execution(* com.liwenli.service.UserServiceImpl.*(..))"/>-->
<!--        <aop:pointcut id="pointcut3" expression="execution(* com.liwenli.service.UserServiceImpl.*(..))"/>-->

<!--        执行环绕增加!-->
<!--        <aop:advisor advice-ref="beforeLog" pointcut-ref="pointcut"/>-->

<!--        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>-->
<!--    </aop:config>-->


<!--    方式二:自定义类-->
<!--    <bean id="diyPointCut" class="com.liwenli.diy.DiyPointCut"/>-->
<!--    <aop:config>-->
<!--        自定义切面,ref需要引用的类-->
<!--         <aop:aspect ref="diyPointCut">-->
<!--             切入点-->
<!--             <aop:pointcut id="point" expression="execution(* com.liwenli.service.UserServiceImpl.*(..))"/>&lt;!&ndash;一个类下的所有方法&ndash;&gt;-->
<!--             <aop:pointcut id="point" expression="execution(* com.liwenli.service.*.*(..))"/> &lt;!&ndash;一个包下的所有类的所有方法&ndash;&gt;-->
<!--             通知-->
<!--             <aop:before method="before" pointcut-ref="point"/>-->
<!--             <aop:after method="after" pointcut-ref="point"/>-->

<!--         </aop:aspect>-->
<!--    </aop:config>-->


<!--        方式三:Annotation-->
<!--    注册bean-->
    <bean id="annotationPointCut" class="com.liwenli.diy.AnnotationPointCut"/>
<!--    开启注解支持-->
    <aop:aspectj-autoproxy/>



</beans>
~~~~

##MyTest.java(测试类)
1.在src->test->java下新建一个MyTest.java

2.编写MyTest.java
~~~~
import com.liwenli.service.UserService;
import com.liwenli.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

//        UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("userServiceImpl");//动态代理的是的是接口,不是类(这是一个非常容易发生的错误).这是错误的,应该是UserService.java接口,而不是UserServiceImpl.java实现类
//        Object userServiceImpl = context.getBean("userServiceImpl");//默认返回的是Object对象,我们要强转为UserService接口类型

        //        UserService userServiceImpl = (UserService) context.getBean("userServiceImpl");//这是将Object对象强转为UserService接口的正确例子
        UserService userServiceImpl = context.getBean("userServiceImpl", UserService.class);//注意是UserService.java接口,而不是UserServiceImpl.java的实现
        userServiceImpl.add();
        userServiceImpl.delete();
        userServiceImpl.update();
        userServiceImpl.select();

        /*
        * 横切关注点：跨越应用程序多个模块的方法或功能。即是，与我们业务逻辑无关的，但是我们需要关注的部分，就是横切关注点。如日志 , 安全 , 缓存 , 事务等等 ....

        切面（ASPECT）：横切关注点 被模块化 的特殊对象。即，它是一个类。

        通知（Advice）：切面必须要完成的工作。即，它是类中的一个方法。

        目标（Target）：被通知对象。

        代理（Proxy）：向目标对象应用通知之后创建的对象。

        切入点（PointCut）：切面通知 执行的 “地点”的定义。

        连接点（JointPoint）：与切入点匹配的执行点。

        *
        * */
    }
}

~~~~

