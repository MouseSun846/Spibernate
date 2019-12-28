package hibernate.services;

import java.util.List;

public interface Cashier {
    public void checkout(String name, List<String> isbns);
}
