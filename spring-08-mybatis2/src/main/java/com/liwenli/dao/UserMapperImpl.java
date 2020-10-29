package com.liwenli.dao;

import com.liwenli.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class UserMapperImpl implements UserMapper {


//我们的所有操作，都使用sqlSession米执行， 在原米，现在都使SqlSess ionTemplate;

    private SqlSessionTemplate sqlSessionTemplate ;

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public List<User> getUsers() {
        UserMapper mapper = sqlSessionTemplate.getMapper(UserMapper.class);
        return mapper.getUsers();
    }
}
