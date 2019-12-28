package hibernate.services.Impl;

import hibernate.dao.BookShopDao;
import hibernate.services.BookShopServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;


@Service
public class BookShopServicesImpl implements BookShopServices {
    @Autowired
    private BookShopDao bookShopDao;

    /**
     * Spring hibernate 事务流程
     * 1 在方法开始之前
     *  ①获取Session
     *  ②把Session和当前线程绑定，这样就可以在Dao中使用 SessionFactory的
     *      getCurrentSession()方法来获取Session了
     *  ③开启 事务
     * 2、若方法正常结束，既没有出现异常。则
     *  ①提交事务
     *  ②使和当前线程绑定的 Session()接除绑定
     *  ③关闭Session
     * 3、若方法出现异常，则：
     *  ①回滚事务
     *  ②使和当前绑定的Session()接触绑定
     *  ③关闭Session
     * @param username
     * @param isbn
     */
    @Override
    public void purchase(String username, String isbn) {
          int price = bookShopDao.findBookPriceByIsbn(isbn);
          bookShopDao.updateBookStock(isbn);
        try {
            bookShopDao.updateUserAccount(username,price);
        } catch (AccountException e) {
            e.printStackTrace();
        }
    }
}
