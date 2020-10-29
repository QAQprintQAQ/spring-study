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
