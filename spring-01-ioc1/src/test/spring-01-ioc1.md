#spring-01-ioc1
##com.liwenli.dao
1.在src->main->java中新建com.liwenli.dao
2.在com.liwenli.dao下新建一个UserDao.java接口类
3.在com.liwenli.dao下新建UserDao.java的实现类 UserDaoImpl.java,UserDaoMysqlImpl,UserDaoOracleImpl.java,UserDaoSqlserver.java

* UserDaoImpl.java
~~~~
package com.liwenli.dao;

public class UserDaoImpl implements UserDao {

    public void getUser() {
        System.out.println("默认获取用户数据");
    }
}
~~~~

* UserDaoMysqlImpl.java
~~~~
package com.liwenli.dao;

public class UserDaoMysqlImpl implements UserDao{
    @Override
    public void getUser() {
        System.out.println("MySql获取用户数据");
    }
}

~~~~

* UserDaoOracleImpl
~~~~
package com.liwenli.dao;

public class UserDaoOracleImpl implements UserDao{
    @Override
    public void getUser() {
        System.out.println("Oracle获取用户数据");
    }
}

~~~~

* UserDaoSqlserverImpl.java
~~~~
package com.liwenli.dao;

public class UserDaoSqlserverImpl implements UserDao {
    @Override
    public void getUser() {
        System.out.println("Sqlserver获取用户数据");
    }
}

~~~~

##com.liwenli.service
1.在src->main->java下新建com.liwenli.service
2.在com.liwenli.service包下新建一个UserService.java接口
~~~~
package com.liwenli.service;

public interface UserService {

    public void getUser();
}

~~~~
3.在com.liwenli.service下新建一个UserService.java的实现类UserServiceImpl.java
~~~~
 package com.liwenli.service;
 
 import com.liwenli.dao.*;
 
 public class UserServiceImpl implements UserService {
 
 
/*   
     需要service服务端来控制用户的创建,MyTest无法控制!
     
 */
     private   UserDao userDao = new UserDaoImpl();
     private   UserDao userDao2 = new UserDaoMysqlImpl();
     private   UserDao userDao3 = new UserDaoOracleImpl();
     private   UserDao userDao4 = new UserDaoSqlserverImpl();
 
 
 
     public void getUser() {
         userDao.getUser();
         userDao2.getUser();
         userDao3.getUser();
         userDao4.getUser();
 
     }
 }

~~~~

##MyTest
1.在src->test下新建MyTest.java
2.编写MyTest.java
~~~~
import com.liwenli.service.UserService;
import com.liwenli.service.UserServiceImpl;

public class MyTest {

    public static void main(String[] args) {
        UserService service = new UserServiceImpl();

        service.getUser();
    }

}

~~~~

##小结
* 在这种模式下,MyTest.java不能控制用户的创建,每次不同的客户UserDaoOracleImpl,UserDaoMysqlImpl,UserDaoImpl,UserDaoSqlserver访问时都需要通过服务器端Service来控制用户的创建
* 当不同用户创建都需要修改服务端UserServiceImpl这种成本是非常高的
* 我们可以通过将创建用户的控制权交给MyTest这个客户(具体做法就是在UserServiceImpl.java里面设置一个set()方法)
