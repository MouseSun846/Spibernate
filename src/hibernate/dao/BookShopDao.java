package hibernate.dao;

import javax.security.auth.login.AccountException;

public interface BookShopDao {
    //������Ż�ȡ��ĵ���
    public int findBookPriceByIsbn(String isbn);
    //������Ŀ�棬ʹ��Ŷ�Ӧ���
    public void updateBookStock(String isbn);
    //�����û����˻���ʹusername��balance - price
    public void updateUserAccount(String username, int price) throws AccountException;

}
