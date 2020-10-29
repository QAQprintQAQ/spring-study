package com.liwenli.pojo;

import com.liwenli.config.MyConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
//        User user =(User) context.getBean("getUser");//可以强转,也可以通过添加一个类的参数明确显示类型像下面这样
        User user = context.getBean("getUser", User.class);

        System.out.println(user.getName());



    }
}
