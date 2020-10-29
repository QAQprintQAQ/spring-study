#spring-02-ioc2
* 通过spring框架修改spring-01-ioc2
##ApplicationContext.xml
~~~~
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="userDaoImpl" class="com.liwenli.dao.UserDaoImpl"/>
    <bean id="userDaoMysqlImpl" class="com.liwenli.dao.UserDaoMysqlImpl"/>
    <bean id="userDaoOracleImpl" class="com.liwenli.dao.UserDaoOracleImpl"/>
    <bean id="userDaoSqlserverImpl" class="com.liwenli.dao.UserDaoSqlserverImpl"/>


    <bean id="userServiceImpl" class="com.liwenli.service.UserServiceImpl">
        <!--引用另外一个bean , 不是用value 而是用 ref-->
<!--        <property name="userDao" ref="userDaoImpl"/>-->
<!--        <property name="userDao" ref="userDaoMysqlImpl"/>-->
<!--        <property name="userDao" ref="userDaoOracleImpl"/>-->

<!--        我们彻底不用再程序中去改动了 , 要实现不同的操作 , 只需要在xml配置文件中进行修改 , 所谓的IoC,一句话搞定 : 对象由Spring 来创建 , 管理 , 装配 ! -->

        <property name="userDao" ref="userDaoSqlserverImpl"/>

    </bean>
</beans>
~~~~

##MyTest.java
~~~~
import com.liwenli.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    public static void main(String[] args) {
        //解析beans.xml文件 , 生成管理相应的Bean对象
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        //getBean : 参数即为spring配置文件中bean的id .
//        UserServiceImpl userServiceImpl1 =(UserServiceImpl) context.getBean("userServiceImpl");
            /*
                两种方式:一种是通过强转成UserServiceImpl类,加如不强转将返回Object类
                另一种是显示地表明userServiceImpl是UserServiceImpl.class
             */
        UserServiceImpl userServiceImpl = context.getBean("userServiceImpl", UserServiceImpl.class);
        userServiceImpl.getUser();

    }
}

~~~~

##小结
* 到了现在 , 我们彻底不用再程序中去改动了 , 要实现不同的操作 , 只需要在xml配置文件中进行修改 , 所谓的IoC,一句话搞定 : 对象由Spring 来创建 , 管理 , 装配 ! 

