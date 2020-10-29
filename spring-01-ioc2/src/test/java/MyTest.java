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