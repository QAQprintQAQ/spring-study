import com.liwenli.service.UserService;
import com.liwenli.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

//        UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("userServiceImpl");//动态代理的是的是接口,不是类(这是一个非常容易发生的错误).这是错误的,应该是UserService.java接口,而不是UserServiceImpl.java实现类
//        Object userServiceImpl = context.getBean("userServiceImpl");//默认返回的是Object对象,我们要强转为UserService接口类型

        //        UserService userServiceImpl = (UserService) context.getBean("userServiceImpl");//这是将Object对象强转为UserService接口的正确例子
        UserService userServiceImpl = context.getBean("userServiceImpl", UserService.class);//注意是UserService.java接口,而不是UserServiceImpl.java的实现
        userServiceImpl.add();
    }
}


