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
