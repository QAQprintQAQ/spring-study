package com.liwenli.pojo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void testMethodnormal() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        User user = (User) context.getBean("user");
        System.out.println("--------普通的装配----------");

        user.getCat().shout();
        user.getDog().shout();
    }


    @Test
    public void testMethodAutowireByName() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        User userautowirebyname = (User) context.getBean("userautowirebyname");
        System.out.println("--------test autowire='byName'----------");

        userautowirebyname.getCat().shout();
        userautowirebyname.getDog().shout();
    }

    @Test
    public void testMethodAutowireByType() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        User userautowirebytype = (User) context.getBean("userautowirebytype");
        System.out.println("--------test autowire='byType'----------");
        userautowirebytype.getCat().shout();
        userautowirebytype.getDog().shout();
    }
}
