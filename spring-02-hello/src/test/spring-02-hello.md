#spring-02-hello
##前期准备
###建立spring-study父工程
1.新建maven工程
~~~~
   <groupId>com.liwenli</groupId>
    <artifactId>spring-study</artifactId>
~~~~
2.将工程下面的src删除

3.导入spring包(依赖)
* 导入一个spring-webmvc包会同时下载所有需要的spring包
~~~~
 <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.2.9.RELEASE</version>
        </dependency>
    </dependencies>
~~~~

4.导入junit依赖
~~~~
  <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13</version>
            <scope>test</scope>
        </dependency>
~~~~
5.刷新maven将自动自动下载spring相关包

###建立spring-02-hello子工程

1.右键spring-study项目

2.新建moudle
~~~~
 <parent>
        <artifactId>spring-study</artifactId>
        <groupId>com.liwenli</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>

<artifactId>spring-02-hello</artifactId>
~~~~

##编写代码
###pojo实体类(Hello.java)
1.src->main->java新建com.liwenli.pojo包

2.在com.liwenli.pojo包下新建实体类Hello.java

3.编写Hello.java
~~~~
package com.liwenli.pojo;

public class Hello {
    
    private String str;
//注意先不用写有参构造,必须写set()方法
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "str='" + str + '\'' +
                '}';
    }
}

~~~~

##spring配置文件(ApplicationContext.xml)
1.在src->main->sources新建文件ApplicatioinContext.xml
* 内容可以去spring官方文档复制
~~~~
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="hello" class="com.liwenli.pojo.Hello">
<!--bean就是java对象 , 由Spring创建和管理-->
        <property name="str" value="spring"/>
    </bean>
</beans>

~~~~

##测试类(MyTest.java)
1.在src->test->java下新建MyTest.java
~~~~
import com.liwenli.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    public static void main(String[] args) {
 //解析beans.xml文件 , 生成管理相应的Bean对象
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
 //getBean : 参数即为spring配置文件中bean的id .
        Hello hello = context.getBean("hello", Hello.class);
        System.out.println(hello.toString());

    }
}

~~~~
##错误
* 错误“ Java：不支持发行版本5”的正确解决方案

方法: 在普通的Maven项目中，您可以将上述代码粘贴到构建配置文件pom.xml中，以设置目标语言级别。
  
 ~~~~
<properties>
      <maven.compiler.source>1.8</maven.compiler.source>
      <maven.compiler.target>1.8</maven.compiler.target>
  </properties>
~~~~

##思考
* Hello 对象是谁创建的 ?  hello 对象是由Spring创建的
  
*  Hello 对象的属性是怎么设置的 ?  hello 对象的属性是由Spring容器设置的
  
* IOC创建对象方式？ 默认通过无参构造方法来创建,手动配置可实现有参构造方法来创建

* 这个过程就叫控制反转 :
  
  控制 : 谁来控制对象的创建 , 传统应用程序的对象是由程序本身控制创建的 , 使用Spring后 , 对象是由Spring来创建的
  
  反转 : 程序本身不创建对象 , 而变成被动的接收对象 .
  
  依赖注入 : 就是利用set方法来进行注入的.
  
  IOC是一种编程思想，由主动的编程变成被动的接收
  
  可以通过newClassPathXmlApplicationContext去浏览一下底层源码 .
