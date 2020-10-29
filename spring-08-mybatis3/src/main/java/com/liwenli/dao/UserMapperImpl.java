package com.liwenli.dao;

import com.liwenli.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;


/*
//第一种spring整合mybatis
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
 */


public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {

    //spring整合mybatis的方式是继承SqlSessionDaoSupport类,将自动地产生一个sqlSession

    @Override
    public List<User> getUsers() {
        /*
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);//这里一定要用UserMapper接口,不是UserMapperImpl
        return mapper.getUsers();

         */

        return getSqlSession().getMapper(UserMapper.class).getUsers();//这里一定要用UserMapper接口,不是UserMapperImpl

    }
}