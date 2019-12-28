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
     * Spring hibernate ��������
     * 1 �ڷ�����ʼ֮ǰ
     *  �ٻ�ȡSession
     *  �ڰ�Session�͵�ǰ�̰߳󶨣������Ϳ�����Dao��ʹ�� SessionFactory��
     *      getCurrentSession()��������ȡSession��
     *  �ۿ��� ����
     * 2��������������������û�г����쳣����
     *  ���ύ����
     *  ��ʹ�͵�ǰ�̰߳󶨵� Session()�ӳ���
     *  �۹ر�Session
     * 3�������������쳣����
     *  �ٻع�����
     *  ��ʹ�͵�ǰ�󶨵�Session()�Ӵ���
     *  �۹ر�Session
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
