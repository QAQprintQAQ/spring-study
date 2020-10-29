import com.liwenli.pojo.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        User user = context.getBean("user", User.class);
        user.show();
        User user2 = context.getBean("user2", User.class);
        user2.show();
        User user3 = context.getBean("user3", User.class);
        user3.show();
        User user4 = context.getBean("user4", User.class);
        user4.show();
    }
}
