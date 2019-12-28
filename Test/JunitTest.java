import hibernate.services.BookShopServices;
import hibernate.services.Cashier;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.Arrays;

import static myprint.Print.println;

public class JunitTest {
    private ApplicationContext ctx = null;
    private BookShopServices bookShopServices = null;
    private Cashier cashier = null;
    {
        ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        bookShopServices = ctx.getBean(BookShopServices.class);
        cashier = ctx.getBean(Cashier.class);
    }

    @Test
    public void testCashier(){
        cashier.checkout("aa", Arrays.asList("1001","1002"));
    }

    @Test
    public void testBookShopServices(){
        bookShopServices.purchase("aa","1001");
    }


    @Test
    public void testDataSource() {
        DataSource dataSource = ctx.getBean(DataSource.class);
        try {
            println(dataSource.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
