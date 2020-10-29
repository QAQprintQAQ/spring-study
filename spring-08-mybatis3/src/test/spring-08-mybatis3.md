#spring-08-mybatis2
##准备
* spring整合mybatis官方mybatis-spring
* 在pom.xml(spring-study)中添加整合mybatis的相关依赖jar包
~~~~
 
<!--     spring 要操作数据库就需要一个spring-jdbc-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.2.9.RELEASE</version>
        </dependency>


        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.5</version>
        </dependency>


~~~~

##pojo实体类(User.java)
1.在src->main-java下新建一个com.liwenli.pojo包

2.在com.liwenli.pojo包下新建一个User.java实体类

3.编写User.java实体类

~~~~
package com.liwenli.pojo;

public class User {
    private String name;
    private   int id;
    private String pwd;


    public User() {

    }

    public User(String name, int id, String pwd) {
        this.name = name;
        this.id = id;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}

~~~~

##dao
1.在src-main-java下新建一个com.liwenli.dao包

2.在com.liwenli.dao包下新建UserMapper.java(接口),UserMapperImpl.java,UserMapper.xml

3.编写代码
* UserMapper.java(接口)
~~~~
package com.liwenli.dao;

import com.liwenli.pojo.User;

import java.util.List;

public interface UserMapper {

   public List<User> getUsers();

}

~~~~
* UserMapperImpl.java
~~~~
package com.liwenli.dao;

import com.liwenli.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;


/*
//第一种spring整合mybatis
public class UserMapperImpl implements UserMapper {


//我们的所有操作，都使用sqlSession米执行， 在原米，现在都使SqlSess ionTemplate;

    private SqlSessionTemplate sqlSessionTemplate ;

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public List<User> getUsers() {
        UserMapper mapper = sqlSessionTemplate.getMapper(UserMapper.class);
        return mapper.getUsers();
    }

}
 */


public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {

    //spring整合mybatis的方式是继承SqlSessionDaoSupport类,将自动地产生一个sqlSession

    @Override
    public List<User> getUsers() {
        /*
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);//这里一定要用UserMapper接口,不是UserMapperImpl
        return mapper.getUsers();

         */

        return getSqlSession().getMapper(UserMapper.class).getUsers();//这里一定要用UserMapper接口,不是UserMapperImpl

    }
}
~~~~

* UserMapper.xml
~~~~
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.liwenli.dao.UserMapper">

    <select id="getUsers" resultType="com.liwenli.pojo.User">
        select * from user;
    </select>

</mapper>
~~~~

##db.properties
1.在src->main-resources下新建db.properties

2.编写db.properties
~~~~
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/mybatis
username=liwenli
password=liwenli

~~~~

##mybatis核心配置(mybatis-config.xml)
1.在src->main-resources下新建一个mybatis-config.xml

2.编写mybatis-config.xml
~~~~
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <properties resource="db.properties"/>
<!--    导入数据库配置文件,这个文件默认是放在resources西面的,如果放在其他的位置记得换换一下文件路径,不然就会报错-->

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>

        <environment id="test">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis"/>
                <property name="username" value="liwenli"/>
                <property name="password" value="liwenli"/>
            </dataSource>
        </environment>
    </environments>

<!--    <mappers>-->
<!--这个映射必须只有一个,如果在spring配置文件ApplicationContext.xml中已经注册过mapper.xml映射文件,则这里不应该在写这个mappers标签,不然会报错-->
<!--        <mapper resource="com/liwenli/dao/UserMapper.xml"/>-->

<!--    </mappers>-->

</configuration>
~~~~

##spring核心配置(ApplicationContext.xml)
1.在src->main-resources下新建ApplicationContext.xml

2.编写ApplicationContext.xml
~~~~
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--DataSource-->

    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/mybatis"/>
        <property name="username" value="liwenli"/>
        <property name="password" value="liwenli"/>

    </bean>

    <!--sqlSessionFactory-->

    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!--            需要绑定mybatis配置文件,当然这些也可以在mybatis-config.xml中进行设置,不是必须要的-->
                    <property name="configLocation" value="classpath:mybatis-config.xml"/>
                    <property name="mapperLocations" value="com/liwenli/dao/UserMapper.xml"/>


    </bean>

    <!--    sqlSessionTemplate就是我们使用的sqlSession, 是比较规范的名字,然而我们已经习惯了sqlSession的名字 -->
    <bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
        <!--         <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">-->

        <!--             只能使用构造器注入seqSessionFactory,因为它没有set()-->
        <constructor-arg index="0" ref="sqlSessionFactory"/>

    </bean>

<!--    一个UserMapper.java接口的实现类 第一种方法-->
<!--    <bean id="userMapperImpl" class="com.liwenli.dao.UserMapperImpl">-->
<!--        <property name="sqlSessionTemplate" ref="sqlSessionTemplate"/>-->
<!--</bean> -->

<!--    第二种方法-->
<bean id="userMapperImpl" class="com.liwenli.dao.UserMapperImpl">

    <property name="sqlSessionFactory" ref="sqlSessionFactory" />
</bean>

</beans>
~~~~

##MyTest.java(测试类)
1.在src->test-java下新建一个com.liwenli.dao包

2.在com.liwenli.dao包下新建一个MyTest.java

3.编写MyTest.java
~~~~
package com.liwenli.dao;

import com.liwenli.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest{


    public static void main(String[] args) throws IOException {

        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserMapper userMapper = context.getBean("userMapperImpl", UserMapper.class);

        for (User user : userMapper.getUsers()) {

            System.out.println(user);
        }
    }

}

~~~~
##小结

