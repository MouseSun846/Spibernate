package hibernate.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
    private Integer id;
    private String username;
    private int balance;

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
