import com.liwenli.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    public static void main(String[] args) {
        //解析beans.xml文件 , 生成管理相应的Bean对象
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        //getBean : 参数即为spring配置文件中bean的id .
        Hello hello = context.getBean("hello", Hello.class);
        System.out.println(hello.toString());

    }
}
