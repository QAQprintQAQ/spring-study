package com.liwenli.pojo;

public class User {

    private String name;
    /*
    当有参构造函数存在时候,我们要显式的写出User.java的无参构造函数,要不然有参构造函数就会覆盖无参构造函数,导致无参构造函数失效
     */
    public User() {
        System.out.println("user无参构造方法");
    }
    /*
    有参构造函数用于测试spring构造对象的非默认方式(即通过无参构造函数的方式)
     */
    public User(String name) {
        this.name = name;
    }
    /*
    set() 一定要存在,不然spring无法使用
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    public void show(){
        System.out.println("name="+ name );
    }


}
