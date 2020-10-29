package com.liwenli.pojo;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void testPnamespace() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
         /*
            写了有参构造函数后一定要显式地写一个无参构造函数要不然测试会报错
            (因为p namespace需要无参构造函数,而c namespace需要有参构造函数,如果写了有参构造会覆盖掉无参构造函数,因此需要显式地写一个无参构造函数)
         */

        User user = context.getBean("user", User.class);
        System.out.println(user.toString());
    }


    @Test
    public void testCnamespace() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
         /*
            写了有参构造函数后一定要显式地写一个无参构造函数要不然测试会报错
            (因为p namespace需要无参构造函数,而c namespace需要有参构造函数,如果写了有参构造会覆盖掉无参构造函数,因此需要显式地写一个无参构造函数)
         */

        User user2 = context.getBean("user2", User.class);
        System.out.println(user2.toString());
    }



    }

