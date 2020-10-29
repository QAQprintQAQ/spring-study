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
