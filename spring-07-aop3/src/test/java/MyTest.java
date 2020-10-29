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
        userServiceImpl.delete();
        userServiceImpl.update();
        userServiceImpl.select();

        /*
        * 横切关注点：跨越应用程序多个模块的方法或功能。即是，与我们业务逻辑无关的，但是我们需要关注的部分，就是横切关注点。如日志 , 安全 , 缓存 , 事务等等 ....

        切面（ASPECT）：横切关注点 被模块化 的特殊对象。即，它是一个类。

        通知（Advice）：切面必须要完成的工作。即，它是类中的一个方法。

        目标（Target）：被通知对象。

        代理（Proxy）：向目标对象应用通知之后创建的对象。

        切入点（PointCut）：切面通知 执行的 “地点”的定义。

        连接点（JointPoint）：与切入点匹配的执行点。

        *
        * */
    }
}


