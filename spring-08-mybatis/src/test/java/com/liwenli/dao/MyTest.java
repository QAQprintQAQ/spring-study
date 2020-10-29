package com.liwenli.dao;

import com.liwenli.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest{


    public static void main(String[] args) throws IOException {

        //没有sqlSession工具类时候自己写
        String resources = "mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(resources);//抛出异常

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);

        SqlSession sqlSession = sessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.getUsers();
        for (User user : users) {
            System.out.println(user);
        }

    }

}
