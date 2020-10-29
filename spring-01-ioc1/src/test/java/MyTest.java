import com.liwenli.service.UserService;
import com.liwenli.service.UserServiceImpl;

public class MyTest {

    public static void main(String[] args) {
        UserService service = new UserServiceImpl();

        service.getUser();
    }

}
