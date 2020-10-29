#spring-04-di1
##实体类pojo(Student.java,Address.java)
###Address.java

1.在src->main->java下新建com.liwenli.pojo包

2.在com.liwenli.pojo包下新建Address.java类

3.编写Address.java类
~~~~
package com.liwenli.pojo;

public class Address {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                '}';
    }
}

~~~~

###Student.java
1.在com.liwenli.pojo包下新建Student.java类

2.编写Student.java类
~~~~
package com.liwenli.pojo;

import java.util.*;

public class Student {
    private String name;
    private Address address;
    private String[] books;
    private List<String> hobbies;
    private Map<String,String> card;
    private Set<String> games;
    private String wife;
    private Properties info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String[] getBooks() {
        return books;
    }

    public void setBooks(String[] books) {
        this.books = books;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Map<String, String> getCard() {
        return card;
    }

    public void setCard(Map<String, String> card) {
        this.card = card;
    }

    public Set<String> getGames() {
        return games;
    }

    public void setGames(Set<String> games) {
        this.games = games;
    }

    public String getWife() {
        return wife;
    }

    public void setWife(String wife) {
        this.wife = wife;
    }

    public Properties getInfo() {
        return info;
    }

    public void setInfo(Properties info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", books=" + Arrays.toString(books) +
                ", hobbies=" + hobbies +
                ", card=" + card +
                ", games=" + games +
                ", wife='" + wife + '\'' +
                ", info=" + info +
                '}';
    }
}

~~~~

##核心配置(ApplicationContext.xml)
1.在src->main->sources下新建ApplicationContext.xml)

2.编写ApplicationContext.xml文件
~~~~
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

<!--    托管Address类-->
    <bean id="addr" class="com.liwenli.pojo.Address">
        <property name="address" value="湖南省郴州市"/>
    </bean>

<!--    托管Student类-->
    <bean id="student" class="com.liwenli.pojo.Student">
<!--        第一种常量注入name:String-->
        <property name="name" value="小明"/>
<!--        第二种Bean注入address:Address。这里的值是一个引用,ref -->
        <property name="address" ref="addr"/>
<!--        第三种数组注入books:String[] -->
        <property name="books">
            <array>
                <value>西游记</value>
                <value>红楼梦</value>
                <value>水浒传</value>
            </array>
        </property>
<!--        第四种List注入hobbies:List<String>-->
        <property name="hobbies">
            <list>
                <value>听歌</value>
                <value>电影</value>
                <value>爬山</value>
            </list>
        </property>

<!--        第五种Map注入card:Map<String,String> -->
        <property name="card">
            <map>
                <entry key="邮政" value="456456456465456"/>
                <entry key="建设" value="145668225551132"/>
            </map>
        </property>

<!--        第六种set注入games:Set<String>-->
        <property name="games">
            <set>
                <value>LOL</value>
                <value>BOB</value>
                <value>COC</value>
            </set>
        </property>

<!--        第七种Null注入-->

        <property name="wife"><null/></property>

<!--        第八种Properties注入-->
        <property name="info">
            <props>
                <prop key="学号">20190604</prop>
                <prop key="性别">男</prop>
                <prop key="姓名">小明</prop>
            </props>
        </property>

    </bean>

</beans>
~~~~

##测试类(MyTest)
1.在src->test下新建com.liwenli.pojo包
2.在src->test下面的com.liwenli.pojo包新建MyTest.java文件
3.编写MyTest.java
~~~~
package com.liwenli.pojo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    public static void main(String[] args) {

    }

    @Test
    public void testName(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student = (Student) context.getBean("student");

        System.out.println(student.getName());

    }

    @Test
    public void testToString(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student = (Student) context.getBean("student");

        System.out.println(student.toString());

    }
}

~~~~
