#spring-05-anno2
##pojo
1.在src->main->java下新建包com.liwenli.pojo

2.在com.liwenli.pojo下新建User.java

3.编写User.java
~~~~
package com.liwenli.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("user")// @Conponent组件相当于配置文件中 <bean id="user" class="当前注解的类"/>
/*
@Component三个衍生注解

为了更好的进行分层，Spring可以使用其它三个注解，功能一样，目前使用哪一个功能都一样。
@Component:pojo

@Controller：web层

@Service：service层

@Repository：dao层

写上这些注解，就相当于将这个类放到spring容器中并交给Spring管理装配了！

 */
@Scope("singleton")//作用域 原型模式@Scope("protoType") 设为单例模式/原型模式
public class User {

//    public String name = "李文利"//如果不初始化,即写成public String name;则会在测试类中出现"name==null"

//    public String name;//如果不像上面那样赋值的话,就会出现"name==null",这时候我们可以使用@Value注解组件进行赋值

    @Value("liwenli")//使用@Value注解组件进行赋值。相当于配置文件中 <property name="name" value="秦疆"/>
    public String name;

}

~~~~

##controller
1.在src->main->java新建com.liwenli.controller包

2.在com.liwenli.controller中新建UserController.java

3.编写UserController.java
~~~~
package com.liwenli.controller;

import org.springframework.stereotype.Controller;

@Controller//相当于@Component,用于在ApplicationContext.xml进行注册UserController类
public class UserController {
}

~~~~

##dao
1.在src->main->java下新建com.liwenli.dao包

2.在com.liwenli.dao包下新建UserDao.java

3.编写UserDao.java
~~~~
package com.liwenli.dao;

import org.springframework.stereotype.Repository;

@Repository//相当于@Component,用于在ApplicationContext.xml进行注册UserDao类
public class UserDao {

}

~~~~

##service
1.在src->main->java中新建一个com.liwenli.service包

2.在com.liwenli.service下新建一个UserService.java

3.编写UserService.java
~~~~
package com.liwenli.service;

import org.springframework.stereotype.Service;

@Service//相当于@Component,用于在ApplicationContext.xml进行注册UserService类
public class UserService {
}

~~~~

##核心配置(ApplicationContext.xml)
1.在src->main->resources下新建一个ApplicationContext.xml

2.编写ApplicationContext.xml
~~~~
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

<!--    指定要扫描的包,这个包下的注解就会生效-->
    <context:component-scan base-package="com.liwenli"/>
<!--    使用spring注解必须要加上的,注解配置-->
    <context:annotation-config/>

</beans>
~~~~

##测试类(MyTest.java)
1.在src->test下新建一个com.liwenli.pojo包

2.src->test下的com.liwenli.pojo新建一个MyTest.java

3.编写MyTest.java
~~~~
package com.liwenli.pojo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
//        User user = (User) context.getBean("user");//这样也行,就是需要将Object强转为User类
        User user = context.getBean("user", User.class);
        System.out.println(user.name);
        System.out.println("User类已经在ApplicatonContext.xml中注册!");

    }
}

~~~~

##小结

###xml与注解:
 * xml更加万能，适用于任何场合!维护简单方
 * 注解不是自己类使用不了，维护相对复杂!
### xml与注解最佳实践:
* xml用来管理bean;
* 注解只负责完成属性的注入;
* 我们在使用的过程中，只需要注意-个问题:必须让注解生效,就需要开启注解的支持
~~~~
     http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

<!--    指定要扫描的包,这个包下的注解就会生效-->
    <context:component-scan base-package="com.liwenli"/>
<!--    使用spring注解必须要加上的,注解配置-->
    <context:annotation-config/>



~~~~
