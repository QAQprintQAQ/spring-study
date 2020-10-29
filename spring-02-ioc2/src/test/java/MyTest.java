import com.liwenli.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    public static void main(String[] args) {
        //解析beans.xml文件 , 生成管理相应的Bean对象
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        //getBean : 参数即为spring配置文件中bean的id .
//        UserServiceImpl userServiceImpl1 =(UserServiceImpl) context.getBean("userServiceImpl");
            /*
                两种方式:一种是通过强转成UserServiceImpl类,加如不强转将返回Object类
                另一种是显示地表明userServiceImpl是UserServiceImpl.class
             */
        UserServiceImpl userServiceImpl = context.getBean("userServiceImpl", UserServiceImpl.class);
        userServiceImpl.getUser();

    }
}
