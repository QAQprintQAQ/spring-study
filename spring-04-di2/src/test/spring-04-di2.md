#spring-04-di2
##pojo实体类(User.java)
* p namespace 需要加上外部依赖: xmlns:p="http://www.springframework.org/schema/p"
* c namespace 需要加上外部依赖: xmlns:c="http://www.springframework.org/schema/c"


1.在src->main->java新建com.liwenli.pojo包

2.在com.liwenli.pojo下新建User.java类

3.编写User.java类
~~~~
package com.liwenli.pojo;

public class User {
    private String name;
    private int age;

    public User() {
        /*
            写了有参构造函数后一定要显式地写一个无参构造函数要不然测试会报错
            (因为p namespace需要无参构造函数,而c namespace需要有参构造函数,如果写了有参构造会覆盖掉无参构造函数,因此需要显式地写一个无参构造函数)
         */
    }
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

~~~~

##核心配置(ApplicationContext.xml)
1.在src->main->sources下新建一个ApplicationContext.xml
2.编写ApplicationContext.xml
~~~~
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">


    <!--P(属性: properties)命名空间 , 属性依然要设置set方法-->
    <bean id="user" class="com.liwenli.pojo.User" p:name="李文利" p:age="18"/>

    <!--C(构造: Constructor)命名空间 , 属性依然要设置set方法-->
<!--    发现问题：爆红了，刚才我们没有写有参构造！-->
    <bean id="user2" class="com.liwenli.pojo.User" c:name="李文利" c:age="18"/>

</beans>
~~~~

##测试类(MyTest.java)
1.在src->test下新建一个com.liwenli.pojo包

2.在src->test下的com.liwenli.pojo包下新建一个MyTest.java类

3.编写MyTest.java类
~~~~
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

~~~~