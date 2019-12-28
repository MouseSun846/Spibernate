package hibernate.services.Impl;

import hibernate.services.BookShopServices;
import hibernate.services.Cashier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashierImpl implements Cashier {

    @Autowired
    private BookShopServices bookShopServices;
    @Override
    public void checkout(String name, List<String> isbns) {
        for (String isbn:isbns){
            bookShopServices.purchase(name,isbn);
        }

    }
}
