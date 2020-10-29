package com.liwenli.pojo;

public class User {
    private Cat cat;
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
    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

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
