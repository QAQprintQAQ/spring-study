#spring-07-aop
##准备
* 在pom.xml(spring-study)导入aop相关依赖坐标
~~~~

        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.6</version>
        </dependency>

~~~~

##service
1.在src->main->java下新建一个com.liwenli.service包

2.在com.liwenli.service包下新建UserService.java(接口),UserServiceImpl.java

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

##log
1.在src->main->java下新建一个com.liwenli.log包

2.在com.liwenli.log包下新建AfterLog.java,BeforeLog.java

3.编写代码

* BeforeLog.java
~~~~
package com.liwenli.log;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
//前置log
public class BeforeLog implements MethodBeforeAdvice {
    @Override
    /*
        method:要执行的目标对象的方法
        args:参数
        target:目标对象

     */
    public void before(Method method, Object[] args, Object target) throws Throwable {

        System.out.println(target.getClass().getName() + "的" + method.getName() + "被执行了");


    }
}

~~~~

* AfterLog.java
~~~~
package com.liwenli.log;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;
//后置log
public class AfterLog implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("执行了" + method.getName() + "方法,返回结果为:" + returnValue);

    }
}

~~~~

##ApplicationContext.xml
1.在src->main->resources下新建ApplicationContext.xml

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
    <aop:config>
<!--     切入点:expresesion:表达式,execution(要执行的位置！* * * * *)   -->
        <aop:pointcut id="pointcut" expression="execution(* com.liwenli.service.UserServiceImpl.*(..))"/>
<!--        <aop:pointcut id="pointcut2" expression="execution(* com.liwenli.service.UserServiceImpl.*(..))"/>-->
<!--        <aop:pointcut id="pointcut3" expression="execution(* com.liwenli.service.UserServiceImpl.*(..))"/>-->

<!--        执行环绕增加!-->
        <aop:advisor advice-ref="beforeLog" pointcut-ref="pointcut"/>

        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
    </aop:config>


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
    }
}

~~~~

##小结
