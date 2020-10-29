#spring-05-anno
##准备
~~~~
* 开启注解支持
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

<!--    <bean id="dog" class="com.liwenli.pojo.Dog"/>-->
    <bean id="dogQualifier" class="com.liwenli.pojo.Dog"/>
    <bean id="cat" class="com.liwenli.pojo.Cat"/>
    <bean id="user" class="com.liwenli.pojo.User"/>

</beans>
~~~~
##实体类pojo
1.在src->main-java中新建com.liwenli.pojo包
2.在com.liwenli.pojo下新建Dog.java,Cat.java,User.java
3.编写Dog.java,Cat.java,User.java
###Cat.java
~~~~
package com.liwenli.pojo;

public class Cat {
    public void shout() {
        System.out.println("miao~");
    }
}

~~~~
###Dog.java
~~~~
package com.liwenli.pojo;

public class Dog {
    public void shout() {
        System.out.println("wang~");
    }
}

~~~~

###User.java
~~~~
package com.liwenli.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

public class User {
    @Autowired
    /*
     @Autowired是根据类型自动装配的，加上@Qualifier则可以根据byName的方式自动装配
       可以写在属性名上，也可以写在set()方法上，官方推荐写在set()方法上。
       @Autowired写在属性名上面的时候可以不用写get(),set()方法

     */
    private Cat cat;

    @Autowired(required = false)
    /*
        @Autowired(required=false)
        说明：false，对象可以为null；true，对象必须存对象，不能为null。
     */
    @Qualifier(value = "dogQualifier")
    /*
        @Autowired是根据类型自动装配的，加上@Qualifier则可以根据byName的方式自动装配
        如果@Autowired自动装配的环境比较复杂，自动装配无法通过一个注解[@Autowired]完成的时候
        我们可以使用@Qualifier(value="xx" )去配置@Autowired的使用，指定一个唯一的bean对象注入!
     */
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
    @Autowired//官方推荐写在这里
    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    @Autowired
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
1.在src->main->resources新建一个ApplicationContext.xml

2.编写ApplicationContext.xml
~~~~
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

<!--    <bean id="dog" class="com.liwenli.pojo.Dog"/>-->
    <bean id="dogQualifier" class="com.liwenli.pojo.Dog"/>
    <bean id="cat" class="com.liwenli.pojo.Cat"/>
    <bean id="user" class="com.liwenli.pojo.User"/>

</beans>
~~~~

##编写测试类(MyTest.java)
1.src->test下新建一个com.liwenli.pojo包

2.在src->test下的com.liwenli.pojo包下新家一个MyTest.java

3.编写MyTest.java
~~~~ 
package com.liwenli.pojo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void testAutowired() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        User user = (User) context.getBean("user");
        System.out.println("------测试@Autowired注解------");
        user.getCat().shout();
        user.getDog().shout();
    }

    @Test
    public void testDogQualifier() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        User user = (User) context.getBean("user");
        System.out.println("------测试@Autowired@Qualifier注解------");
        user.getCat().shout();
        user.getDog().shout();
        System.out.println("如果@Autowired 默认的dog找不到,@Qualifier(value = dogQualifier)也是可以注入的！！");
    }

}

~~~~

##注意事项
* 使用spring注解的时候一定要注意导入相关的约束,spring官网上复制即可
~~~~
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
~~~~

* 本节重点掌握@Autowired @Qualifier 的使用
~~~~
 /*
     @Autowired是根据类型自动装配的，加上@Qualifier则可以根据byName的方式自动装配
       可以写在属性名上，也可以写在set()方法上，官方推荐写在set()方法上。
       @Autowired写在属性名上面的时候可以不用写get(),set()方法

     */

 /*
        @Autowired是根据类型自动装配的，加上@Qualifier则可以根据byName的方式自动装配
        如果@Autowired自动装配的环境比较复杂，自动装配无法通过一个注解[@Autowired]完成的时候
        我们可以使用@Qualifier(value="xx" )去配置@Autowired的使用，指定一个唯一的bean对象注入!
     */
~~~~
* 使用@Autowired @Qualifier 时候一定要导入约束 
 ~~~~
http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>
~~~~