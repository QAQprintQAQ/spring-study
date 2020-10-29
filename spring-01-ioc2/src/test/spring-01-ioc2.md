#spring-01-ioc2
* 环境基于spring-01-ioc1
##改进UserServiceImpl.java
~~~~
package com.liwenli.service;

import com.liwenli.dao.UserDao;
import com.liwenli.dao.UserDaoImpl;
import com.liwenli.dao.UserDaoMysqlImpl;
import com.liwenli.dao.UserDaoOracleImpl;

public class UserServiceImpl implements UserService {


    private  UserDao userDao;

   /*
    我们并没有像spring-01-ioc1那样,在这里就创建了一个UserDao.java的实现类
    我们这里向外部提供了一个set方法,这样只要MyTest.java中需要新建UserDao实现类就可以调用这个方法从而创建一个UserDao实现类
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUser() {
        userDao.getUser();

    }
}

~~~~

##MyTest.java
~~~~
import com.liwenli.dao.UserDaoImpl;
import com.liwenli.dao.UserDaoMysqlImpl;
import com.liwenli.dao.UserDaoOracleImpl;
import com.liwenli.dao.UserDaoSqlserverImpl;
import com.liwenli.service.UserService;
import com.liwenli.service.UserServiceImpl;

public class MyTest {

    public static void main(String[] args) {

        /*
            显而易见,创建UserDao.java实现类的权利已经通过UserServiceImpl.java的set()而转移到了MyTest.java的手中,
            MyTest.java需要新的UserDao.java实现类时候就调用UserServiceImpl.java的set()就可以了
         */
        UserService service = new UserServiceImpl();
  
        ((UserServiceImpl) service).setUserDao(new UserDaoImpl());
        service.getUser();

        ((UserServiceImpl) service).setUserDao(new UserDaoMysqlImpl());
        service.getUser();

        ((UserServiceImpl) service).setUserDao(new UserDaoOracleImpl());
        service.getUser();

        ((UserServiceImpl) service).setUserDao(new UserDaoSqlserverImpl());
        service.getUser();

    }
}
~~~~

##小结
* UserServiceImpl.java中的set()方法,成功的将创建用户的权力交给了MyTest.java
* 这种思想 , 从本质上解决了问题 , 我们程序员不再去管理对象的创建了 , 更多的去关注业务的实现 . 耦合性大大降低 . 这也就是IOC的原型 !
* 控制反转IoC(Inversion of Control)，是一种设计思想，DI(依赖注入)是实现IoC的一种方法，也有人认为DI只是IoC的另一种说法
* IoC是Spring框架的核心内容，使用多种方式完美的实现了IoC，可以使用XML配置，也可以使用注解，新版本的Spring也可以零配置实现IoC。
* Spring容器在初始化时先读取配置文件，根据配置文件或元数据创建与组织对象存入容器中，程序使用时再从Ioc容器中取出需要的对象。
* 控制反转是一种通过描述（XML或注解）并通过第三方去生产或获取特定对象的方式。在Spring中实现控制反转的是IoC容器，其实现方法是依赖注入（Dependency Injection,DI）。