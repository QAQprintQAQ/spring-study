#spring-08-mybatis
##准备
* spring整合mybatis官方mybatis-spring
* 在pom.xml(spring-study)中添加mybatis的相关依赖坐标
~~~~

        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.5</version>
        </dependency>
  
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.21</version>
        </dependency>

~~~~

##pojo实体类
1.在src->main->java中新建一个com.liwenli.pojo包

2.在com.liwenli.pojo包下新建一个实体类User.java

3.编写Uesr.java
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
1.在src-main-java下新建一个com.liwenli.dao 包

2.在com.liwenli.dao包下新建UserMapper.java,UserMapper.xml

3.编写代码
* UserMapper.java
~~~~
package com.liwenli.dao;

import com.liwenli.pojo.User;

import java.util.List;

public interface UserMapper {

   public List<User> getUsers();

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
* mysql 8.0的 driver=com.mysql.cj.jdbc.Driver,不是com.mysql.jdbc.Driver
1.在src->main—resources下新建一个db.properties

2.编写db.properties

~~~~
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/mybatis
username=liwenli
password=liwenli

~~~~

##mybatis-config.xml

1.在src-main->resources下新建一个mybatis-config.xml

2.编写mybatis-config.xml

~~~~
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <properties resource="db.properties"/>

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

    <mappers>

        <mapper resource="com/liwenli/dao/UserMapper.xml"/>

    </mappers>

</configuration>
~~~~

##MyTest.java
1.在src-test->java下新建一个com.liwenli.dao包

2.在com.liwenli.dao包下新建一个MyTest.java

3.编写MyTest.java
~~~~
package com.liwenli.dao;

import com.liwenli.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest{


    public static void main(String[] args) throws IOException {

        //没有sqlSession工具类时候自己写
        String resources = "mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(resources);//抛出异常

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);

        SqlSession sqlSession = sessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.getUsers();
        for (User user : users) {
            System.out.println(user);
        }

    }

}

~~~~
* 注意这里没有工具类,因此我们要手动地获取sqlSession

