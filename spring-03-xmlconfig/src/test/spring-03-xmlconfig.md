 #spring-03-xmlconfig
 * ApplicationContext.xml重要元素
 ##ApplicationContext.xml
~~~~
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">


    <bean id="user" class="com.liwenli.pojo.User" name="useralias,useralias2" scope="singleton">
 <!--
      id 是bean的标识符,要唯一,如果没有配置id,name就是默认标识符.如果配置id,又配置了name,那么name是别名。
      name可以设置多个别名,可以用逗号,分号,空格隔开
      如果不配置id和name,可以根据applicationContext.getBean(.class)获取对象;

-->
<!--          scope="singleton" 表示同一个类的对象只产生一个对象。-->
        <property name="name" value="李文利"/>
     </bean>

    <!--alias别名-->
    <alias name="user" alias="useralias3"/>


<!--    import标签将多个配置文件合并为一个(将bean.xml,bean2.xml,bean3.xml合并为ApplicationContext.xml这个总的配置文件)-->
    <import resource="bean.xml"/>
    <import resource="bean2.xml"/>
    <import resource="bean3.xml"/>
    
<!--    scope="prototype"测试-->
    <bean id="user2" class="com.liwenli.pojo.User" scope="prototype">
        <property name="name" value="liwenli"/>
    </bean>


</beans>

~~~~

##测试(MyTest)

~~~~
import com.liwenli.pojo.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        User user = context.getBean("user", User.class);
        user.show();
        /*
            user的name别名测试
         */
        System.out.println("-------测试name的两个别名-------");
        User useralias = context.getBean("useralias", User.class);
        useralias.show();
        User useralias2 = context.getBean("useralias2", User.class);
        useralias.show();
        /*
            user的alias别名测试
         */
        System.out.println("--------测试alias别名---------");
        User useralias3 = context.getBean("useralias3", User.class);
        useralias.show();

        System.out.println("-------测试scope='singleton'----------");
        User usersingleton = context.getBean("user", User.class);
        /*
            这里的user在ApplicationContext.xml中已经注册过了,在注册时候就已经产生了对象,按理说是不会产生新的对象,下面是测试的结果
         */
        usersingleton.show();
        if (user == usersingleton) {
            System.out.println(user == usersingleton );
            System.out.println("user==usersingleton"+" 因此user和usersingleton是同一个对象");
        }


        /*
           测试scope="prototype"
         */
        System.out.println("---------测试scope='prototype------------'");
        User user2 = context.getBean("user2", User.class);
        user2.show();
        User user2prototype = context.getBean("user2", User.class);
        user2prototype.show();
        if (user2 == user2prototype) {
            System.out.println("user2==user2prototype");

        }else {

            System.out.println("user2!=user2prototype!!!!!,user2和user2prototype不是同一个对象");
        }


    }

}


~~~~
