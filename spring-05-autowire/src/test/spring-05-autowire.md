#spring-05-autowire
##环境搭建
###pojo实体类(User.java,Dog.java,Cat.java)
* 在src->main->java新建com.liwenli.pojo包
* Dog.java
~~~~
package com.liwenli.pojo;

public class Dog {
    public void shout() {
        System.out.println("wang~");
    }
}

~~~~

* Cat.java
~~~~
package com.liwenli.pojo;

public class Cat {
    public void shout() {
        System.out.println("miao~");
    }
}

~~~~

* User.java
~~~~
package com.liwenli.pojo;

public class User {
    private Cat cat;
    private Dog dog;
    private String str;

    public User() {

    }

    public User(Cat cat, Dog dog, String str) {
        this.cat = cat;
        this.dog = dog;
        this.str = str;
    }

    public Cat getCat() {
        return cat;
    }
//将查找其类中所有的set方法名，例如setCat，获得将set去掉并且首字母小写的字符串，即cat。

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "User{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", str='" + str + '\'' +
                '}';
    }
}

~~~~

##核心配置(ApplicationContext.xml)
* 在src->main->sources新建ApplicationContext.xml
~~~~
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

<!--    普通的装配-->
    <bean id="dog" class="com.liwenli.pojo.Dog"/>
    <bean id="cat" class="com.liwenli.pojo.Cat"/>

    <bean id="user" class="com.liwenli.pojo.User">
        <property name="cat" ref="cat"/>
        <property name="dog" ref="dog"/>
        <property name="str" value="李文利"/>
    </bean>

<!--    autowire="byName"自动装配-->

    <bean id="userautowirebyname" class="com.liwenli.pojo.User" autowire="byName"/>

    <!--    autowire="byType"自动装配-->

    <bean id="userautowirebytype" class="com.liwenli.pojo.User" autowire="byType"/>

</beans>
~~~~

##测试类(MyTest.java)
* 在src->test新建com.liwenli.pojo包
* 新建MyTest.java
~~~~
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

~~~~

##小结
###autowire ByName
* 当一个bean节点带有 autowire byName的属性时。
  将查找其类中所有的set方法名，例如setCat，获得将set去掉并且首字母小写的字符串，即cat。
  去spring容器中寻找是否有此字符串名称id的对象。
  如果有，就取出注入；如果没有，就报空指针异常。
  
  
 ###autowire byType
*  autowire byType (按类型自动装配)
  使用autowire byType首先需要保证：同一类型的对象，在spring容器中唯一。如果不唯一，会报不唯一的异常。