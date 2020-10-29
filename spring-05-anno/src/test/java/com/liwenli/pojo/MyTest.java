package com.liwenli.pojo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void testAutowired() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        User user = (User) context.getBean("user");
        System.out.println("------测试@Autowired注解------");
        user.getCat().shout();
        user.getDog().shout();
    }

    @Test
    public void testDogQualifier() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        User user = (User) context.getBean("user");
        System.out.println("------测试@Autowired@Qualifier注解------");
        user.getCat().shout();
        user.getDog().shout();
        System.out.println("如果@Autowired 默认的dog找不到,@Qualifier(value = dogQualifier)也是可以注入的！！");
    }



}
