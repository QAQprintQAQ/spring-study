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
