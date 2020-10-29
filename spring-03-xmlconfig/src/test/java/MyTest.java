import com.liwenli.pojo.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        User user = context.getBean("user", User.class);
        user.show();
        /*
            user的name别名测试
         */
        System.out.println("-------测试name的两个别名-------");
        User useralias = context.getBean("useralias", User.class);
        useralias.show();
        User useralias2 = context.getBean("useralias2", User.class);
        useralias.show();
        /*
            user的alias别名测试
         */
        System.out.println("--------测试alias别名---------");
        User useralias3 = context.getBean("useralias3", User.class);
        useralias.show();

        System.out.println("-------测试scope='singleton'----------");
        User usersingleton = context.getBean("user", User.class);
        /*
            这里的user在ApplicationContext.xml中已经注册过了,在注册时候就已经产生了对象,按理说是不会产生新的对象,下面是测试的结果
         */
        usersingleton.show();
        if (user == usersingleton) {
            System.out.println(user == usersingleton );
            System.out.println("user==usersingleton"+" 因此user和usersingleton是同一个对象");
        }


        /*
           测试scope="prototype"
         */
        System.out.println("---------测试scope='prototype------------'");
        User user2 = context.getBean("user2", User.class);
        user2.show();
        User user2prototype = context.getBean("user2", User.class);
        user2prototype.show();
        if (user2 == user2prototype) {
            System.out.println("user2==user2prototype");

        }else {

            System.out.println("user2!=user2prototype!!!!!,user2和user2prototype不是同一个对象");
        }


    }

}

