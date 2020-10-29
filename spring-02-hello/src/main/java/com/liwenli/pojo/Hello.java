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

