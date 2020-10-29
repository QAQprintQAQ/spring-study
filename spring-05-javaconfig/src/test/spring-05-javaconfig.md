#spring-05-javaconfig
##pojo
1.在src->main->java下新建一个com.liwenli.pojo包

2.在com.liwenli.pojo下新建一个User.java实体类

3.编写User.java
~~~~
package com.liwenli.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/*
    将这个类标注为Spring的一个组件，放到容器中！
 */
@Component//经过测试没有@component也能成功地在spring中进行注册,没有也能通过MyTest.java的测试
public class User {

    private String name;

    public String getName() {
        return name;
    }

    @Value("李文利")//name属性值注入spring!
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}

~~~~

##MyConfig(java配置类用于替代ApplicationContext.xml)
1.在src->main->java 新建一个com.liwenli.config包

2.在com.liwenli.config下新建一个MyConfig.java ,MyConfig2.java配置类

3.编写MyConfig.java 
~~~~
package com.liwenli.config;

import com.liwenli.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration //代表这是一个配置类
@ComponentScan("com.liwenli.pojo")//可以显式地扫描包实现该包下地类地注册,当然也可以不写
@Import(MyConfig2.class)//相当于ApplicationContext.xml中的import标签用来合并其他地配置文件(类).这里式合并MyConfig2.class配置类//导入合并其他配置类，类似于配置文件中的import标签
public class MyConfig {

    @Bean//通过方法注册一个bean，这里的返回值(User)就Bean的类型，方法名(getUser)就是bean的id！
    public User getUser() {

        return new User();
    }

}

~~~~

4.编写MyConfig2.java
~~~~
package com.liwenli.config;

import org.springframework.context.annotation.Configuration;

@Configuration//代表这是一个配置类,用来测试MyConfig.class中的@Import注解组件(合并配置类)
public class MyConfig2 {

}

~~~~

##MyTest.java(测试类)
1.src->test下新建一个com.liwenli.pojo包

2.在src->test下的com.liwenli.pojo下新建一个MyTest.java

3.编写MyTest.java
~~~~
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

~~~~

##小结
* 本节主要是要@Configuration,@Bean spring注解组件