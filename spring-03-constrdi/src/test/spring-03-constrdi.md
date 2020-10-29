#spring-03-constrdi
* spring容器如何创建对象
## 环境准备
###pojo实体类(User.java)
1.在src->main->java新建com.liwenli.pojo

2.在com.liwenli.pojo新建User.java实体类
~~~~
package com.liwenli.pojo;

public class User {

    private String name;
    /*
    当有参构造函数存在时候,我们要显式的写出User.java的无参构造函数,要不然有参构造函数就会覆盖无参构造函数,导致无参构造函数失效
     */
    public User() {
        System.out.println("user无参构造方法");
    }
    /*
    有参构造函数用于测试spring构造对象的非默认方式(即通过无参构造函数的方式)
     */
    public User(String name) {
        this.name = name;
    }
    /*
    set() 一定要存在,不然spring无法使用
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    public void show(){
        System.out.println("name="+ name );
    }


}

~~~~
###Application.xml
1.在src->main->resources下建立ApplicationContext.xml
~~~~
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

<!--    当无有参构造函数时spring通过User.java的无参构造函数的默认地构造新对象-->
    <bean id="user" class="com.liwenli.pojo.User">
        <property name="name" value="李文利"/>
     </bean>
<!--    下面的都是spring通过User.java有参构造函数的非默认构造方式-->
    <!-- 第一种根据index参数下标设置 -->
    <bean id="user2" class="com.liwenli.pojo.User">
        <!-- index指构造方法 , 下标从0开始 -->
        <constructor-arg index="0" value="李文利"/>
    </bean>
    <!-- 第二种根据参数名字设置 -->
    <bean id="user3" class="com.liwenli.pojo.User">
        <!-- name指参数名 推荐使用-->
        <constructor-arg name="name" value="李文利"/>
    </bean>
    <!-- 第三种根据参数类型设置  不推荐使用-->
    <bean id="user4" class="com.liwenli.pojo.User">
        <constructor-arg type="java.lang.String" value="李文利"/>
    </bean>
</beans>
~~~~

###测试(MyTest.java)
1.在src->test->java下新建MyTest.java
~~~~
import com.liwenli.pojo.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        User user = context.getBean("user", User.class);
        user.show();
        User user2 = context.getBean("user2", User.class);
        user2.show();
        User user3 = context.getBean("user3", User.class);
        user3.show();
        User user4 = context.getBean("user4", User.class);
        user4.show();
    }
}
~~~~
##小结
* spring的默认构造对象方式为利用实体类(User.java)的无参构造函数进行构造新对象
~~~~
<!--    当无有参构造函数时spring通过User.java的无参构造函数的默认地构造新对象-->
    <bean id="user" class="com.liwenli.pojo.User">
        <property name="name" value="李文利"/>
     </bean>

~~~~
* 有参构造有三种方式构造新对象
~~~~
<!-- 第一种根据index参数下标设置 -->
<bean id="userT" class="com.kuang.pojo.UserT">
   <!-- index指构造方法 , 下标从0开始 -->
   <constructor-arg index="0" value="kuangshen2"/>
</bean>
<!-- 第二种根据参数名字设置 -->
<bean id="userT" class="com.kuang.pojo.UserT">
   <!-- name指参数名 -->
   <constructor-arg name="name" value="kuangshen2"/>
</bean>
<!-- 第三种根据参数类型设置 -->
<bean id="userT" class="com.kuang.pojo.UserT">
   <constructor-arg type="java.lang.String" value="kuangshen2"/>
</bean>
~~~~
* 尽管如此,在有参构造方式的三种方法中我们只需要掌握第二种即根据参数名字设置就够用了!
 ~~~~
<!-- 第二种根据参数名字设置 -->
<bean id="userT" class="com.kuang.pojo.UserT">
   <!-- name指参数名 -->
   <constructor-arg name="name" value="kuangshen2"/>
</bean>
~~~~
* 当然这些方式统称为构造器注入依赖！！！
