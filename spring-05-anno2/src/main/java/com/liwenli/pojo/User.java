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
