package hibernate.dao.Impl;

import hibernate.dao.BookShopDao;
import hibernate.exception.BookStockException;
import hibernate.exception.UserAccountException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class BookShopDaoImpl implements BookShopDao {

    @Autowired
    private SessionFactory sessionFactory;
    /**
     * 不推荐使用HibernateTemplate和HiberateDaoSupport
     * 因为这样使用会导致Dao和Spring的API进行耦合，可移植性变差
     */

    /**
     * 获取与当前线程绑定的session
     * @return
     */
    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int findBookPriceByIsbn(String isbn) {
        String hql = "SELECT b.price FROM Book b WHERE b.isbn = ?";
        Query query = getSession().createQuery(hql).setString(0,isbn);
        return (Integer) query.uniqueResult();
    }

    @Override
    public void updateBookStock(String isbn) {
        //验证书的库存是否充足
        String hql = "SELECT b.stock FROM Book b WHERE b.isbn = ?";
        int stock= (int) getSession().createQuery(hql).setString(0, isbn).uniqueResult();
        if (stock == 0){
            throw new BookStockException("库存不足！！！");
        }

        hql = "UPDATE Book b SET b.stock = b.stock - 1 WHERE b.isbn = ?";
        getSession().createQuery(hql).setString(0,isbn).executeUpdate();

    }

    @Override
    public void updateUserAccount(String username, int price)  {
        //验证余额是否充足
        String hql = "SELECT a.balance FROM Account a WHERE a.username = ?";
        int balance = (int) getSession().createQuery(hql).setString(0, username).uniqueResult();
        if (balance<price){
            throw new UserAccountException("余额不足！！！");
        }
        hql = "UPDATE Account a SET a.balance = a.balance - ?  WHERE a.username = ?";
        getSession().createQuery(hql).setInteger(0,price).setString(1,username).executeUpdate();
    }
}
