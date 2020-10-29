package com.liwenli.pojo;

public class User {
    private String name;
    private int age;

    public User() {
        /*
            写了有参构造函数后一定要显式地写一个无参构造函数要不然测试会报错
            (因为p namespace需要无参构造函数,而c namespace需要有参构造函数,如果写了有参构造会覆盖掉无参构造函数,因此需要显式地写一个无参构造函数)
         */
    }
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
